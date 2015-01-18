README
Cian O'Brien, Raja Raman, Ying-Shu Kuo

* Data Collection
    - Use Eclipse to run the code in /Lastfmfilter.
    - Point the workspace to /Path/To/Poloisawesome.
    - Import project by choosing the root directory as /Path/To/Poloisawesome/Lastfmfilter.
    - Import external libaries (gson-2.3.jar and org.apache.commons.io.jar) by following steps.
      1. Right click on project folder in "Package Explorer".
      2. Select "Build Path" > "Configure Build Path". Should pop out a window: "Properties for Lastfmfilter".
      3. Go click "Libraries" tab and "Remove" existing gson-2.3.jar and org.apache.commons.io.jar.
      4. Go click "Add External JARs" button and search for /Path/To/Lastfmfilter/*.jar to add them.
    - Edit Constants.java
      = Edit "VALID_USERS_ID_FILE", "FILE_VALID_USERS_ID_FILTERED", "DIRECTORY_USERS_JSON_VALID",
        "USERS_JSON_DIRECTORY", "USER_ID_START_POSITION", "USER_ID_END_POSITION", "MINIMUM_PLAY_COUNT".
        We had made default values for you, yet the directory and file path should be modified.
      = We also leave "API_KEY" for you, you can modify to your own last.fm API key if you want :)
    - Run UserInfoFetchImpl.java to collect user data.
      = Make sure the users.txt is in /Path/To/Poloisawesome/Users/.
    - Run FilterUsers.java to filter valid users.
      = Make sure the users.txt is in /Path/To/Poloisawesome/ValidUsers/.
  
* Feature Extraction
    - Extract features from raw last.fm data by executing following script:
      > python features.py
    - Make sure to point variables "directory" and "savePath" specified in features.py
      to the right folder.
    - Details are specified in the paper report.

* Data Visualization
    - Every thing is explained in ./visualization/README.txt.
    - The only thing needs to do before proceed to visualization stage,
      is to copy the feature data to ./visualization/realdata.
      We have copied them for you anyway :)

