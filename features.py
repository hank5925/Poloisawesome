import  os
import  os.path
import  json
import  time
import  datetime
import  calendar
import  csv
from    datetime   import date
from    math       import floor
import numpy

#####################################################################
#                                                                   #
#       Script for calculating and saving features.                 #
#                                                                   #
#       Output  =   48 tab separated text files, each one has       #
#                   one row per user with features for that user    #    
#                   in that month                                   #
#                                                                   #
#####################################################################

# path to the folders:
directory = "/Path/To/ValidUsersSubset"

# save path:
savePath = "/Path/To/ValidUsersFeatures"

# fileDir is an array of arrays { {userId, {JSON files}}, ... }
fileDir = os.walk(directory)
fileDir = list(fileDir)

# allocate feature array = list of lists, one list for each 1-month time period
features = [[] for x in range(0,48)]

start_time = time.time()

# function definitions ###################################################################

# convert local time to UTC  
def getLocal(utc,country):

    offset = -8
    
    # TODO: add all country codes with their offsets
    if country == 'FI':
        offset = 2
    elif country == 'JP':
        offset = 9
    elif country == 'FR':
        offset = 1
    elif country == 'SE':
        offset = 1
    elif country == 'US':
        offset = -8
    elif country == 'ES':
        offset = 1
    elif country == 'HR':
        offset = 1
    elif country == 'BR':
        offset = -2
    elif country == 'PL':
        offset = 1
    elif country == 'AT':
        offset = 1
    elif country == 'AR':
        offset = -3
    elif country == 'UK':
        offset = 0
    elif country == 'NL':
        offset = 1
    elif country == 'UA':
        offset = 2
    elif country == 'RU':
        offset = 3
    elif country == 'CA':
        offset = -5
    elif country == 'PT':
        offset = 1
    elif country == 'DE':
        offset = 1
    elif country == 'DK':
        offset = 1
    elif country == 'ID':
        offset = 7
    elif country == 'IE':
        offset = 0
    elif country == 'IN':
        offset == 5.5
    elif country == 'IO':
        offset = 6
    elif country == 'AU':
        offset = 9
    elif country == 'AQ':
        offset = 7
    elif country == 'VN':
        offset = 7
    elif country == 'IT':
        offset = 1
    elif country == 'IR':
        offset = 3.5
    elif country == 'IS':
        offset = 0
    elif country == 'IQ':
        offset = 3
    elif country == 'AF':
        offset = 4.5
    elif country == 'VE':
        offset = -4
    elif country == 'AE':
        offset = 4
    elif country == 'VA':
        offset = 1
    elif country == 'TH':
        offset = 7
    elif country == 'LT':
        offset = 2
    elif country == 'TN':
        offset = 1
    elif country == 'LI':
        offset = 1
    elif country == 'LK':
        offset = 5.5
    elif country == 'TR':
        offset = 2
    elif country == 'TW':
        offset = 8
    elif country == 'LB':
        offset = 2
    elif country == 'MK':
        offset = 1
    elif country == 'DZ':
        offset = 1
    elif country == 'DO':
        offset = -4
    elif country == 'SG':
        offset = 8
    elif country == 'SA':
        offset = 3
    elif country == 'KZ':
        offset = 5.5
    elif country == 'SL':
        offset = 0
    elif country == 'SO':
        offset = 3
    elif country == 'SH':
        offset = 0
    elif country == 'SI':
        offset = 1
    elif country == 'SJ':
        offset = 1
    elif country == 'SK':
        offset = 1
    elif country == 'SV':
        offset = -6
    elif country == 'KH':
        offset = 7
    elif country == 'KE':
        offset = 3
    elif country == 'CR':
        offset = -6
    elif country == 'CY':
        offset = 2
    elif country == 'CZ':
        offset = 1
    elif country == 'CC':
        offset = 6.5
    elif country == 'CL':
        offset = -4
    elif country == 'CN':
        offset = 8
    elif country == 'CO':
        offset = -5
    elif country == 'CH':
        offset = 1
    elif country == 'NU':
        offset = 12
    elif country == 'NZ':
        offset = 11
    elif country == 'NO':
        offset = 1
    elif country == 'FO':
        offset = 0
    elif country == 'FK':
        offset = -4
    elif country == 'FJ':
        offset = 12
    elif country == 'MA':
        offset = 0
    elif country == 'IL':
        offset = 2
    elif country == 'MX':
        offset = -7
    elif country == 'MY':
        offset = 8
    elif country == 'MT':
        offset = 1
    elif country == 'UM':
        offset = -4
    elif country == 'MH':
        offset = 12
    elif country == 'MO':
        offset = 8
    elif country == 'UY':
        offset = -3
    elif country == 'MD':
        offset = 2
    elif country == 'ME':
        offset = 1
    elif country == 'ZW':
        offset = 2
    elif country == 'EC':
        offset = -5
    elif country == 'ZA':
        offset = 2
    elif country == 'EG':
        offset = 2
    elif country == 'EE':
        offset = 2
    elif country == 'PM':
        offset = -3.5
    elif country == 'PN':
        offset = -8.5
    elif country == 'PH':
        offset = 8
    elif country == 'PK':
        offset = 5
    elif country == 'PE':
        offset = -5
    elif country == 'LV':
        offset = 2
    elif country == 'PR':
        offset = -4
    elif country == 'AD':
        offset = 1
    elif country == 'HN':
        offset = -6
    elif country == 'HK':
        offset = 8
    elif country == 'HU':
        offset = 1
    elif country == 'GL':
        offset = -3
    elif country == 'GE':
        offset = 4
    elif country == 'GF':
        offset = -3
    elif country == 'GP':
        offset = -4
    elif country == 'GR':
        offset = 2
    elif country == 'GS':
        offset = 4
    elif country == 'GT':
        offset = -6
    elif country == 'GU':
        offset = -10
    elif country == 'RO':
        offset = 2
    elif country == 'RS':
        offset = 1
    elif country == 'BZ':
        offset = -6
    elif country == 'BY':
        offset = 2
    elif country == 'BS':
        offset = -5
    elif country == 'JM':
        offset = -5
    elif country == 'BM':
        offset = -4
    elif country == 'BA':
        offset = 1
    elif country == 'BG':
        offset = 2
    elif country == 'BF':
        offset = 0
    elif country == 'BE':
        offset = 1
    elif country == 'BD':
        offset = 6

   
    offset = offset * 3600
    utc    = int(utc) + offset

    localPlaytime = date.fromtimestamp(utc).strftime('%Y%m%d')
   
    hours = floor((utc % 86400) / 3600)
    mins  = floor(((utc % 86400) - hours * 3600) / 60)
    secs  = (utc % 86400) - hours*3600 - mins*60

    localPlayTime = localPlaytime + "%02d" % hours + "%02d" % mins + "%02d" % secs
    
    return localPlayTime

# get the month index  given the local time
def getMonthIdx(localTime):
    
    month    = str(localTime)[4:6]
    year     = str(localTime)[0:4]
    
    constant = 0
    
    if year == '2011':
        constant = 0
    elif year == '2012':
        constant = 12
    elif year == '2013':
        constant = 24
    elif year == '2014':
        constant = 36

    return int(month) + constant

# average plays per day:
def playPerDay(timePeriod):
    
    lastPlayDay     = -1
    totalPlayCount  = list()
    thisPlayCount   = 0
    if len(timePeriod) > 1 :
        for i in range(0, len(timePeriod)):
        
            # get the date of this play  
            playInfo = timePeriod[i][5]
            thisPlayDay  = playInfo[6:8]

            if thisPlayDay == lastPlayDay :        
                thisPlayCount = thisPlayCount + 1
            else :
                totalPlayCount.append(thisPlayCount)          
                thisPlayCount = 0
            
            lastPlayDay = thisPlayDay

        # return average plays per day:
        return list(( sum(totalPlayCount)/len(totalPlayCount), numpy.median(totalPlayCount), numpy.std(totalPlayCount) ))
    else:
        return list((0, 0.0, 0.0))

# average plays per session:
def playPerSession(timePeriod,sessionGap):

    if len(timePeriod) > 0 :
        
        lastTime          = str(3030)
        sessionPlayCount  = 0
        sessionTotalCount = list()

        for i in range(0, len(timePeriod)):
           
            playInfo  = timePeriod[i][5]
            thisTime  = playInfo[10:len(playInfo)]
        
            minDiff = abs(int(thisTime[0:2]) - int(lastTime[0:2]))
            secDiff = int(thisTime[2:4]) - int(lastTime[2:4])

            # we are in the same session:
            if minDiff < sessionGap or (minDiff == sessionGap and secDiff < 0):
                sessionPlayCount = sessionPlayCount + 1

            # this track is the beginning of a new session:
            else :
                sessionTotalCount.append(sessionPlayCount) 
                sessionPlayCount = 0
                
            lastTime = thisTime

            # check if we are at the end of the play list, with only ones session.
            # if we are, need to append the current session playcount
            if i == len(timePeriod)-1 and len(sessionTotalCount) ==0:
                sessionTotalCount.append(sessionPlayCount)
                
        # return average plays per session:
        return list(( sum(sessionTotalCount)/len(sessionTotalCount), numpy.median(sessionTotalCount), numpy.std(sessionTotalCount) ))
    else:
        return list((0, 0.0, 0.0))

# average session length:
def sessionLength(timePeriod,sessionGap):

    # dont count single plays. should these be counted? not sure...
    if len(timePeriod) > 1 :
        
        lastTime           = str(3030)
        endTime            = str(3030)
        startTime          = str(3030)
        sessionLength      = 0
        sessionLengthTotal = list()
        
        for i in range(0, len(timePeriod)):
            
            playInfo    = timePeriod[i][5]
            thisTime    = playInfo[10:len(playInfo)]

            if i == 0:
                startTime = thisTime
                
            minDiff = abs(int(thisTime[0:2]) - int(lastTime[0:2]))
            secDiff = int(thisTime[2:4]) - int(lastTime[2:4])

            # see if this track is the beginning of a new session:
            if minDiff > sessionGap or (minDiff == sessionGap and secDiff > 0):
                # ignore sessions with single track. if we leave this it will give
                # session length == 0 since we don't have access to the track's length.
                # maybe session length == 0 is OK for our purposes?
                
                #if startTime != lastTime :
                endTime = lastTime
                sessionLength = 60*(abs(int(endTime[0:2])-int(startTime[0:2]))) + abs(int(endTime[2:4])-int(startTime[2:4]))
                # session length in minutes:
                sessionLength = sessionLength / 60
                sessionLengthTotal.append(sessionLength)
                startTime = thisTime
                
            lastTime = thisTime

            # check if we are at the end of the play list, with only ones session.
            # if we are, need to append the current session playcount
            if i == len(timePeriod)-1 and len(sessionLengthTotal) ==0:
                sessionLengthTotal.append(sessionLength)
            # if we only had one play, the session length is equal to the tracks length      
            
        # return average session length
        
        #print(sum(sessionLengthTotal)/len(sessionLengthTotal))
        return list(( sum(sessionLengthTotal)/len(sessionLengthTotal), numpy.median(sessionLengthTotal), numpy.std(sessionLengthTotal) ))
    else:
        return ((0, 0.0, 0.0))

# calculate number unique album/artist/tracks per session
def rateChange(timePeriod,sessionGap):

    artistCount = 1
    albumCount  = 1
    trackCount  = 1
    lastTime    = str(3030)

    artistTotal = list()
    albumTotal  = list()
    trackTotal  = list()

    if len(timePeriod) > 1 :
        for i in range(0, len(timePeriod)-1):
        
            playInfo    = timePeriod[i][5]   
            thisTime    = playInfo[10:len(playInfo)]
            minDiff     = abs(int(thisTime[0:2]) - int(lastTime[0:2]))
            secDiff     = int(thisTime[2:4]) - int(lastTime[2:4])
        
            thisArtist = timePeriod[i][2]
            thisAlbum  = timePeriod[i][6]
            thisTrack  = timePeriod[i][4]

            nextArtist = timePeriod[i+1][2]
            nextAlbum  = timePeriod[i+1][6]
            nextTrack  = timePeriod[i+1][4]

            if thisArtist != nextArtist:
                artistCount = artistCount + 1
            if thisAlbum != nextAlbum:
                albumCount = albumCount + 1
            if thisTrack != nextTrack:
                trackCount = trackCount + 1

            # see if this track is the beginning of a new session. if it is, reset the counts
            if minDiff > sessionGap or (minDiff == sessionGap and secDiff > 0):
                artistTotal.append(artistCount)
                artistCount = 0
                albumTotal.append(albumCount)
                albumCount  = 0
                trackTotal.append(trackCount)
                trackCount  = 0

            lastTime = thisTime
 
            # check if we are at the end of the play list, with only ones session.
            # if we are, need to append the current session playcount   
            if i == len(timePeriod)-2 and len(artistTotal) ==0:
                artistTotal.append(artistCount)
            if i == len(timePeriod)-2 and len(albumTotal) ==0:
                albumTotal.append(albumCount)
            if i == len(timePeriod)-2 and len(trackTotal) ==0:
                trackTotal.append(trackCount)
                    
        return list((sum(artistTotal)/len(artistTotal), sum(albumTotal)/len(albumTotal), sum(trackTotal)/ len(trackTotal) ))
    else:
        return list((0,0,0))
    
# average number of plays in interval 0-6, 6-12, 12-18, 18-24
def playsPerInterval(timePeriod):

    morning     = 0;
    afternoon   = 0;
    evening     = 0;
    night       = 0;
    
    if len(timePeriod) > 1 :
        for i in range(0, len(timePeriod)-1):
        
            playInfo    = timePeriod[i][5]
            thisTime    = playInfo[8:10]

            if int(thisTime) > 0 and int(thisTime) <= 6:
                morning = morning + 1
            elif int(thisTime) > 6 and int(thisTime) <= 12:
                afternoon = afternoon + 1
            elif int(thisTime) > 12 and int(thisTime) <= 18:
                evening = evening + 1
            elif int(thisTime) > 18 and int(thisTime) <= 24:
                night = night + 1
            
        return list((morning, afternoon, evening, night))
    else:
        return list((0,0,0,0))

# number of unique tracks per month:
def uniquePerMonth(timePeriod):

    artists = list()
    albums  = list()
    tracks  = list()
    artist_noId = 0
    album_noId  = 0
    track_noId  = 0

    if len(timePeriod) > 0:
        for i in range(0, len(timePeriod)-1):

            artists.append(timePeriod[i][2])
            if timePeriod[i][2] == '':
                artist_noId += 1
            albums.append(timePeriod[i][6])
            if timePeriod[i][6] == '':
                album_noId += 1
            tracks.append(timePeriod[i][4])
            if timePeriod[i][4] == '':
                track_noId += 1

        uniqueArtists = list(set(artists))
        uniqueAlbums  = list(set(albums))
        uniqueTracks  = list(set(tracks))

        # Account for no mbid ids for artists/albums/tracks:
        # Number of unique artists/albums/tracks - 
        if artist_noId > 0:
            numUniqueArtists = len(uniqueArtists) + artist_noId - 1
        else:
            numUniqueArtists = len(uniqueArtists)
        if album_noId > 0:
            numUniqueAlbums = len(uniqueAlbums) + album_noId - 1
        else:
            numUniqueAlbums = len(uniqueAlbums)
        if track_noId > 0:
            numUniqueTracks = len(uniqueTracks) + track_noId - 1
        else:
            numUniqueTracks = len(uniqueTracks)

        return list(( round(float(numUniqueArtists)/float(len(timePeriod)), 4), round(float(numUniqueAlbums)/float(len(timePeriod)), 4), round(float(numUniqueTracks)/float(len(timePeriod)), 4), numUniqueArtists, numUniqueAlbums, numUniqueTracks ))
    
    else:
        return list((0.0,0.0,0.0,0,0,0))


def jsonParseCheck(filename):
    counter_curly = 0
    counter_square = 0
    for line in filename:
        for c in line:
            if c == '{':
                counter_curly += 1
            elif c == '}':
                counter_curly -= 1
            elif c == '[':
                counter_square += 1
            elif c == ']':
                counter_square -= 1
    filename.seek(0)
    if counter_curly == 0 and counter_square == 0:
        return True
    else:
        return False
##########################################################################################

print len(fileDir)
for i in range(1, len(fileDir)) :# for each user      
    if i%100 == 0:
    	print i
    # the folder path:
    userPath = fileDir[i][0]

    # first get the user ID, country, age:
    userInfo = fileDir[i][2][len(fileDir[i][2])-1] # user_info.json
    userInfo = userPath + "/" + userInfo
    userInfo = open(userInfo,'r')
    infoJSON = json.load(userInfo)
    userInfo.close
        
    age         = infoJSON['user']['age']
    ID          = infoJSON['user']['id']
    country     = infoJSON['user']['country']
    playcount   = infoJSON['user']['playcount']

    # declare a list of lists which will store play data for each time period, for this user
    playPeriod = [[] for x in range(0,48)]
        
    for j in range(0, len(fileDir[i][2])-1): # for each JSON, except user_info.json
    	# print ID, fileDir[i][2][j]
        thisPage = userPath + "/" +  fileDir[i][2][j]
        thisFile = open(thisPage, 'r')

        # Check for valid formatting of JSON to pass:
        if jsonParseCheck(thisFile):
            thisFileString = thisFile.read()
            thisJSON = json.loads(thisFileString)
        else:
            thisJSON = []

        thisFile.close()

        # check if the JSON 'recenttracks' and 'track' field exists:
        if 'recenttracks' in thisJSON:
            # recentTracks contains all the track play info:
            # recentTracks = thisJSON['recenttracks']['track']

            if 'track' in thisJSON['recenttracks']:
            	recentTracks = thisJSON['recenttracks']['track']
            else:
            	recentTracks = []

            # for each play, get its uts code, convert to local time
            # and save it to the required data array:
            for k in range (0, len(recentTracks)):  # for each recent track play
                if len(recentTracks) != 8:
					if 'date' in recentTracks[k]:
					    # return local time
						localTime = getLocal(recentTracks[k]['date']['uts'],country) 

					    # get the corresponding month
						month = getMonthIdx(localTime)
					    # add this play to the correct month
						thisList = list((ID,recentTracks[k]['artist']['#text'],recentTracks[k]['artist']['mbid'],recentTracks[k]['name'],recentTracks[k]['mbid'],localTime,
					                     recentTracks[k]['album']['mbid']))
					    #playPeriod[month-1].insert(0,thisList)
						playPeriod[month-1].append(thisList)


    # sort each month by playtime:
    for m in range(0, len(playPeriod)):

        thisUserFeat = list()
            
        # open the file for writing:
        # f = open(savePath + str(m) + ".csv", 'wb')
        #     outFile = csv.writer(csvfile)
        f = open(savePath + '/' + str(m) + '.csv', 'a')
        outFile = csv.writer(f, delimiter='\t', quotechar='|', quoting=csv.QUOTE_MINIMAL)
        # check for empty (= no plays in this month)
        playPeriod[m].sort(key = lambda cell: cell[5])
                
        # calculate features for each month and write them to that month's overall feature array:
        
        # ID:
        thisUserFeat.append(ID)
        # AGE:
        thisUserFeat.append(age)
        # COUNTRY:
        thisUserFeat.append(country)
        # PLAYS PER DAY:
        pd = playPerDay(playPeriod[m])
        thisUserFeat.append(pd[0])
        thisUserFeat.append(pd[1])
        thisUserFeat.append(pd[2])
        # PLAYS PER SESSION:
        ps = playPerSession(playPeriod[m],30)
        thisUserFeat.append(ps[0])
        thisUserFeat.append(ps[1])
        thisUserFeat.append(ps[2])
        # AVERAGE SESSION LENGTH: 
        sl = sessionLength(playPeriod[m],30)
        thisUserFeat.append(sl[0])
        thisUserFeat.append(sl[1])
        thisUserFeat.append(sl[2])
        # RATE OF CHANGE, TRACKS/ARTIST/ALBUM: 
        r = rateChange(playPeriod[m],30)
        thisUserFeat.append(r[0])
        thisUserFeat.append(r[1])
        thisUserFeat.append(r[2])
        # PLAYS PER TIME-OF-DAY, 0:6/6:12/12:18/18:24        
        t = playsPerInterval(playPeriod[m])
        thisUserFeat.append(t[0])
        thisUserFeat.append(t[1])
        thisUserFeat.append(t[2])
        thisUserFeat.append(t[3])
        
        # Unique artists/ albums/ tracks per month:
        um = uniquePerMonth(playPeriod[m])
        thisUserFeat.append(um[0])
        thisUserFeat.append(um[1])
        thisUserFeat.append(um[2])
        thisUserFeat.append(um[3])
        thisUserFeat.append(um[4])
        thisUserFeat.append(um[5])
        # write the features:
        outFile.writerow(thisUserFeat)
        f.close()
                    
print('DONE. Time in seconds:')
print(time.time() - start_time)
