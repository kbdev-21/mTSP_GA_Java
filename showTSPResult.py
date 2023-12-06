import matplotlib.pyplot as plt

def getTSPResultAndVisualization(filePath):
    with open(filePath, 'r') as file:
        x = []
        y = []
        for line in file:
            line = line.strip()[2:-2]
            data = line.split("->")
            for i in range(len(data)):
                coordinates = data[i].replace("(", "").replace(")", "").split(",")
                x.append(int(coordinates[0]))
                y.append(int(coordinates[1]))
        x.append(x[0])
        y.append(y[0])
        plt.figure(figsize=(8, 6))
        plt.plot(x, y, marker='o', linestyle='-', color='b')
        plt.title("TSP Tour")
        plt.xlabel("X Axis")
        plt.ylabel("Y Axis")
        plt.show()
            
getTSPResultAndVisualization("results/tspResult.txt")