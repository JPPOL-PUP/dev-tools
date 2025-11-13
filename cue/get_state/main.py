import logging
import os

import requests
from lxml import etree
logger = logging.getLogger("default")
from xml.etree.ElementTree import Element
import csv
from concurrent.futures import ThreadPoolExecutor, as_completed

escenic_base_url = os.environ.get('ESCENIC_BASE_URL')
escenic_username = os.environ.get('ESCENIC_USERNAME')
escenic_password = os.environ.get('ESCENIC_PASSWORD')

def request_url(content_id: int) -> Element:
    url = f'{escenic_base_url}/webservice/escenic/content/{content_id}'
    logger.info(f"Requesting {url}...")
    response = requests.get(url, allow_redirects=True, auth=(escenic_username, escenic_password),
                            timeout=(10.0, 5.0)
                            )
    logger.info(f"Content: {response.content}")
    if response.status_code != 200:
        raise RuntimeError(f"Non-200 status code from escenic-webservice on url {url}. "
                           f"Response: {response.status_code}: {response.content}")
    return etree.fromstring(response.content)


def get_state(xml: Element) -> str:
    default_namespaces = {
        'atom': 'http://www.w3.org/2005/Atom',
        'vdf': 'http://www.vizrt.com/types',
        'metadata': "http://xmlns.escenic.com/2010/atom-metadata",
        'dcterms': "http://purl.org/dc/terms/",
        'app': "http://www.w3.org/2007/app",
        'vaext': "http://www.vizrt.com/atom-ext"
    }

    return str(xml.xpath(f'app:control/vaext:state/@name',
                         namespaces=default_namespaces)[0])

def process_row(content_id):
    try:
        xml = request_url(content_id)
        state = get_state(xml)
    except Exception as e:
        state = f'{e}'
    return f'{content_id}, {state}'

if __name__ == '__main__':
    with open('input.csv', newline='') as csvfile:
        reader = csv.reader(csvfile)
        content_ids = sorted([int(row[0]) for row in reader])

    results = []
    with ThreadPoolExecutor(max_workers=8) as executor:
        future_to_id = {executor.submit(process_row, cid): cid for cid in content_ids}
        for counter, future in enumerate(as_completed(future_to_id), 1):
            result = future.result()
            results.append(result)
            print(counter)

    with open('output.txt', 'w') as f:
        for item in results:
            f.write(f'{item}\n')