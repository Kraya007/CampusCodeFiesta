import os
import random

file1 = open("vaccinations.csv","r")
file2 = file1.readlines()

def runVaccineBST(dataset):
    with open("vaccinations.csv","r") as file:
        for x in file:
            aId = str(x.strip().split(" ")[0])
            os.system("java -cp bin VaccineBSTApp")
            
            with open("data/VaccineBSTAppCount.txt","r") as countedFile:
                opCount = countedFile.read()
                dataset = str(dataset)
                with open("data/VaccineBSTApp+ ".txt", "a") as counted:
                    counted.write(aId+ " " + opCount + "\n")                   
def ranSet(n):
    aList = [x for x in range(n-1, -1, -1)]
    random.shuffle(aList)
    return aList;
            
for n in range(1,11):
    dataset = n*500
    aList1 = ranSet(dataset)
    aFile = open("data/vaccionations","w")
    for i in range(dataset):
        aFile.write(file2[aList1[i]])
    aFile.close()
    runVaccineBSTApp(dataset)
    
                        

                    