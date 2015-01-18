import string

path = '../realdata/'
ext = '.csv'
#ext2 = '.csvv'
for i in range(48):
    name = path + str(i) + ext
    #name2 = path + str(i) + ext2
    with open(name, 'r') as f:
        data = f.read()
        data_mod = string.replace(data, '\r\n', '\n')

    with open(name, 'w') as g:
        g.write('id\tage\tcountry\tpd1\tpd2\tpd3\tps1\tps2\tps3\tsl1\tsl2\tsl3\t')
        g.write('r1\tr2\tr3\tt1\tt2\tt3\tt4\tum1\tum2\tum3\tum4\tum5\tum6\n')
        g.write(data_mod)
                


