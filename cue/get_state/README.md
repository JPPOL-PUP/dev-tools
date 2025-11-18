# Script for getting content states from CUE
create input.csv with content ids to get states for, one content id per line (no header).

Run the script:
```bash
python3 main.py
```
The output will be written to output.txt

## Environment variables
```bash
CUE_USERNAME=<username>
CUE_PASSWORD=<password>
CUE_BASE_URL=https://cue-dev.jp.dk
```

## Run locally
```bash
python3.12 -m venv venv
source venv/bin/activate
pip3 install --upgrade pip==25.1.1
pip3 install -r requirements.txt
```