## Solve Multiple Traveling Salesman Problem using Genetic Algorithm in Java<br/>
The GA method used in this source code is based on the 2023 article titled '**A Hybrid Genetic Algorithm for the min-max Multiple Traveling Salesman Problem**' by *Sasan Mahmoudinazlou* and *Changhyun Kwon*. <br/><br/>
Here is the link to access the paper: https://arxiv.org/abs/2307.07120 <br/><br/>

**TSP Tour:** 

![Figure_2](https://scontent.xx.fbcdn.net/v/t1.15752-9/369480960_6747636605319054_5269253634964379653_n.png?_nc_cat=105&ccb=1-7&_nc_sid=510075&_nc_ohc=KKZ8kamgao8AX-JQInN&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&oh=03_AdR7E8ZFvv2yXS4Uha3E0aTe6D9jOFOxhyZgv78DpQ-7cA&oe=658860B9)

**Multiple TSP:**

![Figure_3](https://scontent.xx.fbcdn.net/v/t1.15752-9/376602084_772835554727243_3073980217504839308_n.png?_nc_cat=105&ccb=1-7&_nc_sid=510075&_nc_ohc=LcSvhc2lt00AX-fVJcN&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&oh=03_AdSBaPwmzggM8FRlDAfhlmgRgFu-vqqgvTu1IzJe7oNtgQ&oe=6588803D)

## How to run the program<br/>
1. If you want to generate a random dataset, run GenerateDataset.java. The default datasets and the random one are stored in the /datasets folder
2. To start the algorithm, run Main.java. You can choose the dataset that you want to solve there
3. After finish the whole algorithm, the results for TSP and mTSP will be stored in the /results folder
4. To visualize the results, run 2 .py files in /represent_result folder. Note that this feature requires matplotlib(a python library) to be installed to run