print("***** Anagram Finder *****")
import os.path
if os.path.isfile("EnglishWords.txt"):     
    word = input("Enter a word: ")
    file = open("EnglishWords.txt","r")
    arrayfile = file.readlines()
    start = 0
    for i in arrayfile:
        i = i.replace("\n","")
        if(i == "START"):
            break
        start +=1
        
    dictWord = {}
    for k in word:
        dictWord[k] = 1
       
    arrayWord = []
    for x in range(start, len(arrayfile)):
        arrayfile[x] = arrayfile[x].replace("\n","")
        if len(word) == len(arrayfile[x]) :
            dictArry = {}
            for line in arrayfile[x]:
                dictArry[line] = 1
                
            if dictArry == dictWord and word != arrayfile[x]:
                arrayWord.append(arrayfile[x])
                
    if arrayWord == []:
        print()
        print("Sorry, anagrams of '"+word+"' could not be found.")
    else:
        print()
        print(sorted(arrayWord))

else: 
    print("Sorry, could not find file 'EnglishWords.txt'.")