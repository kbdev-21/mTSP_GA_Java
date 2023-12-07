import matplotlib.pyplot as plt

def getTSPResultAndVisualization(filePath):
    # Lists to store X and Y coordinates for the TSP tour
    x = []
    y = []

    # Read data from the file and process it
    with open(filePath, 'r') as file:
        for line in file:
            # Remove unnecessary characters and split the line to get tour path
            line = line.strip()[2:-2]
            data = line.split("->")

            # Extract coordinates from each segment of the tour
            for i in range(len(data)):
                coordinates = data[i].replace("(", "").replace(")", "").split(",")
                x.append(int(coordinates[0]))
                y.append(int(coordinates[1]))

    # Close the TSP tour by adding the starting point to the end
    x.append(x[0])
    y.append(y[0])

    # Plot the TSP tour
    plt.figure(figsize=(8, 6))
    plt.plot(x, y, marker='o', linestyle='-', color='b')
    plt.title("TSP Tour")
    plt.xlabel("X Axis")
    plt.ylabel("Y Axis")
    plt.show()

getTSPResultAndVisualization("results/tspResult.txt")
