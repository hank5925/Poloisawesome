#!/usr/bin/env python
import sys, os, threading, time
import run_dim as dim
import run_visual as vis
import cgi, cgitb; cgitb.enable()

def tsne(perp, eps):
    dim.run_tsne(perp, eps)
    vis.run_tsne(perp, eps)

def pca():
    dim.run()
    vis.run_pca()

def selection(feat1, feat2):
    vis.run(feat1, feat2)

def main():
    form = cgi.FieldStorage()
    if "dim" not in form:
        print "Content-type: text/html"
        print
        sys.stdout.write("-1")
        return

    # dimension reduction = 1 / feature selection = 0
    if int(form["dim"].value) == 1:
        thread = threading.Thread(target = pca)
        thread.daemon = True
        thread.start()
        thread.join()
        print "Content-type: text/html"
        print
        sys.stdout.write("0")
        return
    elif int(form["dim"].value) == 0:
        thread = threading.Thread(target = selection, args = (form["feat1"].value, form["feat2"].value))
        thread.daemon = True
        thread.start()
        thread.join()
        print "Content-type: text/html"
        print
        sys.stdout.write("0")
        return
    elif int(form["dim"].value) == 2:
        thread = threading.Thread(target = tsne, args = (form["perp"].value, form["eps"].value))
        thread.daemon = True
        thread.start()
        thread.join()
        print "Content-type: text/html"
        print
        sys.stdout.write("0")
        return
    else:
        print "Content-type: text/html"
        print
        sys.stdout.write("-2")
        return


if __name__ == "__main__":
    main()
