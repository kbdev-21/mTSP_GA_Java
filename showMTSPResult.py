import matplotlib.pyplot as plt
from typing import List

def getMTSPResultAndVisualization(filePath):
    with open(filePath, 'r') as file:
        salesmanX = []
        salesmanY = []
        for line in file:
            line = line.strip()[2:-2]
            data = line.split("->")
            x = []
            y = []
            for i in range(len(data)):
                coordinates = data[i].replace("(", "").replace(")", "").split(",")
                x.append(int(coordinates[0]))
                y.append(int(coordinates[1]))
            salesmanX.append(x)
            salesmanY.append(y)
        plt.figure(figsize=(8, 6))
        plt.title("Multiple TSP solution")
        plt.xlabel("X Axis")
        plt.ylabel("Y Axis") 
        colors = ["red", "green", "blue", "orange", "purple", "cyan"]
        for i in range(len(salesmanX)):
            salesmanX[i].append(salesmanX[i][0])
            salesmanY[i].append(salesmanY[i][0])
            if(i > 5):
                plt.plot(salesmanX[i], salesmanY[i], marker='o', linestyle='-', color="black")
            else:
                plt.plot(salesmanX[i], salesmanY[i], marker='o', linestyle='-', color=colors[i])
        plt.show()
            
getMTSPResultAndVisualization("results/mtspResult.txt")