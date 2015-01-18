#!/usr/bin/env python
import os
import numpy as np
from sklearn.decomposition import PCA
 
def run():
    # Parameters
    path = "realdata/"
    ext = ".csv"
    out_ext = "_out.csv"

    for i in range(48):
        name = str(i)
        with open(path + name + ext, 'r') as f:
            X = np.loadtxt(f, delimiter='\t', skiprows=1, usecols=tuple(range(25))[3:])
            pca = PCA(n_components=2)
            Y = pca.fit_transform(X)
        with open(path + name + out_ext, 'wb') as g:
            g.write(b'f1\tf2\n')
            np.savetxt(g, Y, delimiter='\t')

def run_tsne(perp, eps):
    # Parameters
    path = "realdata/"
    ext = ".csv"
    out_ext = "_tsne.csv"

    for i in range(48):
        name = str(i)
        inputFile = path + name + ext
        outputFile = path + name + out_ext
        with open(inputFile, 'r') as f:
            cmd = '--input ' + inputFile + '--output' + outputFile + '--no_dims 2 --perplexity ' + perp
            tsne.main(cmd)
            X = np.loadtxt(f, delimiter='\t', skiprows=1, usecols=tuple(range(25))[3:])
            pca = PCA(n_components=2)
            Y = pca.fit_transform(X)
        with open(path + name + out_ext, 'wb') as g:
            g.write(b'f1\tf2\n')
            np.savetxt(g, Y, delimiter='\t')
    # Execute command
    #cmd = cmd + " --input " + inputFile
    #cmd = cmd + " --output " + outputFile
    #cmd = cmd + " --no_dims " + str(dim)
    #cmd = cmd + " --perplexity " + str(perp)
    #cmd = cmd + " &"
    #print cmd
    #os.system(cmd)


