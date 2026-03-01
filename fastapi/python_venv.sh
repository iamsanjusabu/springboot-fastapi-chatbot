#!/bin/bash

set -e

sudo apt update

sudo apt install -y python3 python3-pip python3-venv python-is-python3

python -m venv python_venv

source python_venv/bin/activate

pip install --upgrade pip

pip install -r requirements.txt --no-cache-dir
