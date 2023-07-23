import os
import random

def List(arr):
    with open("kevout.txt", 'r') as f:
        is_It = f.readlines()
        if(len(is_It)>0): 
            print(is_It)
            Array = is_It[1].split(",")
            if (Array[2] == "Not"):
                collisions = int(Array[6])
                if collisions<5:
                    print(arr, "collisions:", collisions)
    f.close()

weights = [1,2,3,4,5,6,7,8,9]
for a in range(0,4):
    weights[0]=a
    for b in range(0,4):
     weights[1]=b
     for c in range(0,4):
        weights[2]=c
        for d in range(0,4):
            weights[3]=d
            for e in range(0,4):
                weights[4]=e
                for f in range(0,4):
                    weights[5]=f
                    for g in range(0,4):
                        weights[6]=g
                        for h in range(0,4):
                            weights[7]=h
                            for i in range(0,4):
                                weights[8]=i
                                os.system(f"java -cp bin C:/Users/kndub/eclipse-workspace/see/src/see/TestHashTable.java {0} {a} {b} {c} {d} {e} {f} {g} {h} {i} > kevout.txt")
                                print(weights)
                                List(weights);

                                