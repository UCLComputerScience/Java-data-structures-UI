from PIL import Image
import numpy as np
from pylab import imshow, show, get_cmap

file1 = "view1.png"
leftImg = Image.open(file1).convert('L')
leftWidth, leftHeight = leftImg.size
print(leftImg.size)

file2 = "view2.png"
rightImg = Image.open(file2).convert('L')
rightWidth, rightHeight = rightImg.size

Z1 = np.random.random((512,512))
imshow(Z1, cmap=get_cmap("gray"), interpolation='nearest')
show()
Z2 = np.random.random((256,256))
imshow(Z2, cmap=get_cmap("gray"), interpolation='nearest')
show()

occlusion = 2.6
disparity_map = [[0]*(leftWidth) for i in range(leftHeight)]
disparity_array = np.asarray(disparity_map)
C = [[0]*(rightWidth+1) for i in range(leftWidth+1)]

def cFunction(z1,z2):
    z = (0.5 * z1) + (0.5 * z2)
    mult1 =  0.5 * (z - z1) * (1/16) * (z -z1)
    mult2 = 0.5 * (z - z2) * (1/16) * (z -z2)
    cFuncOut = mult1 + mult2
    return cFuncOut


def forwardpass(x):
    for i in range(1,leftWidth+1):
        C[i][0] = i * occlusion
    for i in range(1,rightWidth+1):
        C[0][i] = i * occlusion
    for i in range(1, leftWidth+1):
        for j in range (1, rightWidth+1):
            #print(i,j)
            C[i][j] = min(C[i - 1][ j - 1]+ cFunction(leftImg.getpixel((i-1,x)), rightImg.getpixel((j-1,x))), C[i][j-1] + occlusion, C[i-1][j] + occlusion)

def backwardpass(i):
    cur_i = leftWidth
    cur_j = rightWidth
    while cur_i != 0 and cur_j != 0:
        #print(cur_i, cur_j)
        if(C[cur_i][cur_j] == C[cur_i-1][cur_j-1]+cFunction(leftImg.getpixel((cur_i-1,i)), rightImg.getpixel((cur_j-1,i)))):
            disparity_map[i][cur_i-1] = abs(cur_i-cur_j)
            cur_i -=1
            cur_j -=1
        elif(C[cur_i][cur_j] == C[cur_i-1][cur_j] + occlusion):
            disparity_map[i][cur_i-1] = -1
            cur_i -= 1
        elif(C[cur_i][cur_j] == C[cur_i][cur_j-1] + occlusion):
            cur_j -= 1

for i in range(leftHeight):
    print(i)
    C = [[0]*(rightWidth+1) for i in range(leftWidth+1)]
    forwardpass(i)
    backwardpass(i)
disparity_array = np.asarray(disparity_map)
imgOut = Image.fromarray(disparity_array)
imgOut.show()