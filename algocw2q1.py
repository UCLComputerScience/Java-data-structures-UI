import numpy as np
import time
import matplotlib.pyplot as plt
import pandas as pd 
import xlsxwriter
heapCompare = 0
heapSwaps = 0
inputSize = [200000,300000,500000,1000000,5000000,10000000]
heapTimeList = []
quickTimeList = []
heapCompareAndSwapsList = []
quickCompareAndSwapsList = []


def quicksort(list,low,high):
  i = low
  j = high
  swapCount = 0
  compareCount = 0
  pivot = list[high]

  while i <= j:
    while list[i] < pivot:
      i += 1
    while pivot < list[j]:
      j -= 1
    if i <= j:
      aux = list[i]
      list[i] = list[j]
      list[j] = aux
      swapCount += 1
      i += 1
      j -= 1
    compareCount += 1

  if low < j:
    iniSwap, iniCompare = quicksort(list, low, j)
    swapCount += iniSwap
    compareCount += iniCompare
  if i < high:
    iniSwap, iniCompare = quicksort(list, i, high)
    swapCount += iniSwap
    compareCount += iniCompare    

  return (swapCount, compareCount)



def heapify(arr, n, i): 
    global heapSwaps
    global heapCompare
    largest = i # Initialize largest as root 
    l = 2 * i + 1     # left = 2*i + 1 
    r = 2 * i + 2     # right = 2*i + 2 
  
    # See if left child of root exists and is 
    # greater than root 
    if l < n and arr[i] < arr[l]: 
        largest = l 
        heapCompare += 1
  
    # See if right child of root exists and is 
    # greater than root 
    if r < n and arr[largest] < arr[r]: 
        largest = r 
        heapCompare += 1
  
    # Change root, if needed 
    if largest != i: 
        arr[i],arr[largest] = arr[largest],arr[i] # swap 
        heapSwaps += 1
        # Heapify the root. 
        heapify(arr, n, largest) 
    # print(heapCompare, heapSwap)
    return heapCompare, heapSwaps
  
# The main function to sort an array of given size 
def heapSort(arr): 
    n = len(arr) 
    global heapCompare
    global heapSwaps
    # Build a maxheap. 
    for i in range(n, -1, -1): 
        heapify(arr, n, i) 
  
    # One by one extract elements 
    for i in range(n-1, 0, -1): 
        heapSwaps += 1
        arr[i], arr[0] = arr[0], arr[i] # swap 
        heapCompare+=1
        heapify(arr, i, 0)
    return heapCompare, heapSwaps

for i in range (6):
  arr = np.random.randint(0,inputSize[i],inputSize[i])
  n = len(arr) 
  quickStart = time.time()
  quickComparisons, quickSwaps = quicksort(arr,0,n-1) 
  quickEnd = time.time()
  heapStart = time.time()
  heapCompare, heapSwaps = heapSort(arr)
  heapEnd = time.time()
  quickTime = quickEnd - quickStart
  quickTimeList.append(quickTime)
  heapTime = heapEnd - heapStart
  heapTimeList.append(heapTime)
  heapTotalCompareAndSwaps = heapCompare + heapSwaps
  quickTotalCompareAndSwaps = quickComparisons + quickSwaps
  heapCompareAndSwapsList.append(heapTotalCompareAndSwaps)
  quickCompareAndSwapsList.append(quickTotalCompareAndSwaps)
# plt.title("Time taken in seconds")
# plt.plot(sizeLabels,quickTimeList, label = " Qucik Sort")
# plt.plot(sizeLabels,heapTimeList, label = "Heap Sort")
# plt.ylabel('Time Taken')
# plt.xlabel('Input Size')
# plt.legend()
# plt.grid()
# plt.show()

# plt.title("Total Comparisons and Swaps")
# plt.plot(sizeLabels,heapCompareAndSwapsList,label = "Heap Sort")
# plt.plot(sizeLabels,quickCompareAndSwapsList,label = "Quick Sort")
# plt.ylabel("Average Comparisons and Swaps")
# plt.xlabel("Input Size")
# plt.legend()
# plt.grid()
# plt.show()
df = pd.DataFrame({ 'Quick total time': quickTimeList,
                  'Heap total time': heapTimeList,
                  'Quick C&S': quickCompareAndSwapsList,
                  'Heap C&S': heapCompareAndSwapsList })
writer = pd.ExcelWriter('pandas_simple4.xlsx', engine='xlsxwriter')
df.to_excel(writer, sheet_name='run1')
writer.save()
