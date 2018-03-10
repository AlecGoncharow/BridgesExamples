	import requests
import re
import os
from bs4 import BeautifulSoup

javaAssignmentIndex = 6  # Starting index for java code
cppAssignmentIndex = 30  # Starting index for c++ code
APIkey =    # API key as an integer
userName = ""  # userName as a String
javaCodePath = ".\\javaCode\\"  # Directory to output java code in
cppCodePath = ".\\cppCode\\"    # Directory to output c++ code in

if not os.path.exists(javaCodePath):
    os.mkdir(javaCodePath)
if not os.path.exists(cppCodePath):
    os.mkdir(cppCodePath)

URLHeaderHTML = 'http://bridgesuncc.github.io/Hello_World_Tutorials/Tutorial_Header.html'
HeaderPage = requests.get(URLHeaderHTML).text
HTMLHeaderPage = BeautifulSoup(HeaderPage, "html.parser")

linksToTutorials = []
for a in HTMLHeaderPage.find_all('a', href=True):   # Builds a list of individual tutorial links
    linksToTutorials.append(a['href'])

baseUrl = linksToTutorials.pop(0)+'Hello_World_Tutorials/'
tutorialPages = []
for i in linksToTutorials:  # fixes the links and grabs the HTML from each tutorial link
    i = re.sub(r"^./", "", i)
    i = baseUrl + i
    tutorialPages.append(BeautifulSoup(requests.get(i).text, "html.parser"))

javaPageTabs = []
cppPageTabs = []
for page in tutorialPages:   # grabs the link that is relevant to either Java or C++
    for div in page.find_all('div', {'id': 'Java'}):
        javaPageTabs.append(div['w3-include-html'])
    for div in page.find_all('div', {'id': 'C++'}):
        cppPageTabs.append(div['w3-include-html'])

javaTabHTMLs = []
for link in javaPageTabs:   # grabs HTML from the scraped tab link from above
    link = re.sub(r"^./", "", link)
    link = baseUrl + link
    print(link)
    javaTabHTMLs.append(BeautifulSoup(requests.get(link).text, "html.parser"))

cppTabHTMLs = []
for link in cppPageTabs:    # grabs HTML from the scraped tab link from above
    link = re.sub(r"^./", "", link)
    link = baseUrl + link
    print(link)
    cppTabHTMLs.append(BeautifulSoup(requests.get(link).text, "html.parser"))

javaFileDownloads = []
for page in javaTabHTMLs:   # Finds the java code and saves a link to it
    for pre in page.find_all('pre', {'class': 'prettyprint'}):
        if re.search(r"w3-include-html", str(pre)) is not None:
            spanOfSearch = re.search(r"w3-include-html=\".+\"", str(pre)).span()
            link = str(pre)[spanOfSearch[0] + 17: spanOfSearch[1] - 1]
            link = re.sub(r"^./", "", link)
            link = baseUrl + link
            javaFileDownloads.append(link)
for page in javaTabHTMLs:   # Finds the java code and saves a link to it
    for pre in page.find_all('div', {'class': 'prettyprint'}):
        if re.search(r"w3-include-html", str(pre)) is not None:
            spanOfSearch = re.search(r"w3-include-html=\".+\"", str(pre)).span()
            link = str(pre)[spanOfSearch[0] + 17: spanOfSearch[1] - 1]
            link = re.sub(r"^./", "", link)
            link = baseUrl + link
            javaFileDownloads.append(link)


cppFileDownloads = []
for page in cppTabHTMLs:    # Finds the C++ code and saves a link to it
    for pre in page.find_all('pre', {'class': 'prettyprint'}):
        if re.search(r"w3-include-html", str(pre)) is not None:
            spanOfSearch = re.search(r"w3-include-html=\".+\"", str(pre)).span()
            link = str(pre)[spanOfSearch[0] + 17: spanOfSearch[1] - 1]
            link = re.sub(r"^./", "", link)
            link = baseUrl + link
            cppFileDownloads.append(link)
for page in cppTabHTMLs:    # Finds the C++ code and saves a link to it
    for div in page.find_all('div', {'class': 'prettyprint'}):
        if re.search(r"w3-include-html", str(div)) is not None:
            spanOfSearch = re.search(r"w3-include-html=\".+\"", str(div)).span()
            link = str(div)[spanOfSearch[0] + 17: spanOfSearch[1] - 1]
            link = re.sub(r"^./", "", link)
            link = baseUrl + link
            cppFileDownloads.append(link)

javaFiles = []  # Grabs the Java code from the links
for javaFileLink in javaFileDownloads:
    javaFiles.append(requests.get(javaFileLink).text)

cppFiles = []
cppFileNames = []
for cppFileLink in cppFileDownloads:    # Grabs the C++ code and its name
    cppFiles.append(requests.get(cppFileLink).text)
    fileName = re.findall(r"[\w_]+\.cpp|[\w_]+\.h", str(cppFileLink))
    cppFileNames.append(fileName[0])

for javaFile in javaFiles:  # Writes the Java file based on its class and adds in your credentials
    classNameIndex = re.search(r"public class \w+", javaFile).span()
    className = javaFile[classNameIndex[0]+13: classNameIndex[1]]
    newFile = open(javaCodePath + className+".java", "w+")
    javaFile = re.sub(r"\w+,\s+\"YOUR[_\s]*API[_\s]*KEY\",\s+\"YOUR[_\s]USER[_\s]ID\"", str(javaAssignmentIndex)
                      + ", \"" + str(APIkey)
                      + "\", \"" + userName + "\"", javaFile)
    javaFile = re.sub(r"&lt;*", "<", javaFile)   # fixes angled bracket encoding
    javaFile = re.sub(r"&gt;*", ">", javaFile)
    javaAssignmentIndex += 1
    newFile.write(javaFile)
    newFile.close()

for i, cppFile in enumerate(cppFiles):  # Writes the C++ file, naming it based on its File name in the HTM:
    fileName = cppFileNames[i]
    newFile = open(cppCodePath + fileName, "w+")
    cppFile = re.sub(r"\w+,\s+\"YOUR[_\s]USER[_\s]ID\",\s+\"YOUR[_\s]*API[_\s]*KEY\"",
                     str(cppAssignmentIndex) + ", \"" + userName + "\", \"" + str(APIkey)
                     + "\"", cppFile)
    cppFile = re.sub(r"&lt;*", "<", cppFile)    # fixes angled bracket encoding
    cppFile = re.sub(r"&gt;*", ">", cppFile)
    cppAssignmentIndex += 1
    print(cppAssignmentIndex)
    newFile.write(cppFile)
    newFile.close()
