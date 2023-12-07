import matplotlib.pyplot as plt

def getMTSPResultAndVisualization(filePath):
    # Lists to store X and Y coordinates for each salesman's path
    salesmanX = []
    salesmanY = []

    # Read data from the file and process it
    with open(filePath, 'r') as file:
        for line in file:
            # Remove unnecessary characters and split the line to get salesman paths
            line = line.strip()[2:-2]
            data = line.split("->")
            
            # Lists to store X and Y coordinates for the current salesman's path
            x = []
            y = []

            # Extract coordinates from each segment of the path
            for i in range(len(data)):
                coordinates = data[i].replace("(", "").replace(")", "").split(",")
                x.append(int(coordinates[0]))
                y.append(int(coordinates[1]))

            # Append the current salesman's path to the lists
            salesmanX.append(x)
            salesmanY.append(y)

    # Plot the MTSP solution
    plt.figure(figsize=(8, 6))
    plt.title("Multiple TSP solution")
    plt.xlabel("X Axis")
    plt.ylabel("Y Axis") 

    # Define colors for different salesmen
    colors = ["red", "green", "blue", "orange", "purple", "cyan"]

    # Plot each salesman's path
    for i in range(len(salesmanX)):
        # Close the path by adding the starting point to the end
        salesmanX[i].append(salesmanX[i][0])
        salesmanY[i].append(salesmanY[i][0])

        # Use a different color for the first 6 salesmen, black for the rest
        if i > 5:
            plt.plot(salesmanX[i], salesmanY[i], marker='o', linestyle='-', color="black")
        else:
            plt.plot(salesmanX[i], salesmanY[i], marker='o', linestyle='-', color=colors[i])

    # Show the plot
    plt.show()

getMTSPResultAndVisualization("results/mtspResult.txt")