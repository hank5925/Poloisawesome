README
Ying-Shu Kuo, Cian O'Brien, Raja Raman

* How to Use
(1) Run cgi_http_server.py. It will run a simple server on port 9898.
    > ./cgi_http_server.py

(2) Open index.html on browser (Tested on newest version of
    Chrome, Safari on Dec 1 2014).
    http://localhost:9898/index.html

* Files
/cgi-bin
    Contains executive files.
/cgi-bin/run_thread.py
/cgi-bin/run_dim.py
/cgi-bin/run_visual.py
    Python script that are triggered to regenerate main.html for homepage.
/cgi-bin/input_format.py
    Adding header to the feature data.
/css/layouts/*
    Contains css files: PureCSS
/js/*
    Contains Javascript code: d3, jquery, and PureCSS
/realdata/[0-47].csv
    Feature data with 25 features. Each file indicates a specific month
    (Jan 2011 - Dec 2014) from which features are calculated.
/realdata/[0-47]_out.csv
    Feature data after processing PCA. 
/purecss
    Unimportant but necessary documents.
index.html
    Homepage of our project.
main.html
    Tentative to be changed by script.
main_backup.html
    HTML script that generate visualization of feature.
main_pca.html
    HTML script that generate visualization of PCAed feature.
bar_chart.html
    Script that generate bar chart.
empty.html
    HTML script that generate empty page.
cgi_http_server.py
    Simple server features CGI.



