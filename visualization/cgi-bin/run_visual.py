#!/usr/bin/env python
import sys
import string

def run(feat1, feat2):
    with open('main_backup.html', 'r') as f:
        html_doc = f.read()
        html_doc_new = string.replace(html_doc, 'placeholder1', feat1)
        html_doc_new = string.replace(html_doc_new, 'placeholder2', feat2)
    with open('main.html', 'w') as g:
        g.write(html_doc_new)

def run_pca():
    with open('main_pca.html', 'r') as f:
        html_doc = f.read()
    with open('main.html', 'w') as g:
        g.write(html_doc)

def run_tsne():
    with open('main_pca.html', 'r') as f:
        html_doc = f.read()
    with open('main.html', 'w') as g:
        g.write(html_doc)
