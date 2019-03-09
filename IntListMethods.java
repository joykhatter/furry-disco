 public class IntListMethods{
    public static String getAuthorName(){
        return "Khatter, Joy";
    }


    public static String getRyersonID(){
        return "500866988";
    }


    public static void correctNext(int n){
        for(int i=n; i >1; --i){
            IntList.setNext(i, i-1);
        }
    }

    public static int removeIfDivisible(int firstNode, int divideBy){
            int size = (IntList.getAllocatedNodeCount()),
                    previous = firstNode,
                    current = firstNode,
                    next = firstNode,
                    temp = 0,
                    counterOfItemDeleted = 0,
                    key = IntList.getKey(current);
            int a;
            while(current != 0){
                key = IntList.getKey(current);
                if(key % divideBy == 0){                                //if key is divisible by k
                    counterOfItemDeleted++;
                    if(current == previous ){            //if this is first node
                        current = IntList.getNext(current);                     //update current
                        IntList.setNext(previous,0);                        //release previous by setting it's next to 0
                        IntList.release(previous);
                        previous = current;
                        firstNode = current;
                        a = 1;
                    }else if(IntList.getNext(current) == 0){                //else if current node is the last node
                        temp =current;
                        current = IntList.getNext(current);
                        IntList.setNext(temp,0);                        //release current by setting it's next to 0
                        IntList.release(temp);
                        IntList.setNext(previous, 0);
                    }else{
                        IntList.setNext(previous, IntList.getNext(current));
                        temp = current;
                        current = IntList.getNext(current);
                        IntList.setNext(temp,0);                        //release current by setting it's next to 0
                        IntList.release(temp);
                    }
                }else{                                                  //else
                    previous = current;
                    current = IntList.getNext(current);                     //update current
                }
            }
            return firstNode;
    }

    public static int sort(int n){
        int size = IntList.getAllocatedNodeCount();
        int a = mergeSort(size, n, 0);
        return a;
    }

    public static int moveToNode(int start, int nodeNumber){
        int temp=0;
        for(int i=0; i<nodeNumber; i++){
            start = IntList.getNext(start);
        }
        return start;
    }

    public static int mergeSort(int size, int start, int leftPointer){
        int leftListSize = 0, rightListSize=0, startOfLeftArray=0, rightListStart=0, leftListStart=0, startOfList=start;
        int leftKey, rightkey;
        int leftListCurrentPointer=0, listPreviousPointer=0, rightListCurrentPointer=0;
        boolean firstItem = true;
        if(size == 1 ){                     //base case
            return start;
        }
        leftListSize = size/2;                  //divide list in half
        rightListSize = (size-leftListSize);

        startOfLeftArray = moveToNode(start, rightListSize);

        leftListStart = mergeSort(leftListSize, startOfLeftArray, leftPointer);
        rightListStart = mergeSort(rightListSize, start, leftListStart);

        leftListCurrentPointer = listPreviousPointer = leftListStart;
        rightListCurrentPointer =  listPreviousPointer = rightListStart;
        int leftCounter =0, rightCounter=0;
        while(listPreviousPointer != 0) {
            if (leftCounter == leftListSize || rightCounter == rightListSize) {
                if (leftCounter == leftListSize && rightCounter == rightListSize) {
                    IntList.setNext(listPreviousPointer, leftPointer);
                    listPreviousPointer = 0;
                }else if(leftCounter == leftListSize){
                    IntList.setNext(listPreviousPointer, rightListCurrentPointer);
                    listPreviousPointer = rightListCurrentPointer;             //update pointers
                    rightListCurrentPointer = IntList.getNext(rightListCurrentPointer);
                    rightCounter++;
                }else if(rightCounter == rightListSize){
                    IntList.setNext(listPreviousPointer, leftListCurrentPointer);
                    listPreviousPointer = leftListCurrentPointer;             //update pointers
                    leftListCurrentPointer = IntList.getNext(leftListCurrentPointer);
                    leftCounter++;
                }
            } else {
                leftKey = IntList.getKey(leftListCurrentPointer);
                rightkey = IntList.getKey(rightListCurrentPointer);

                if (leftKey < rightkey && leftCounter < leftListSize ) {                    //if left list item is smaller
                    if (firstItem == true) {                      //if item is first in list
                        startOfList = leftListCurrentPointer;        //then left list first element is the first element of list
                        listPreviousPointer = leftListCurrentPointer;
                        firstItem = false;
                        leftListCurrentPointer = IntList.getNext(leftListCurrentPointer);
                        leftCounter++;
                        continue;
                    }
                    IntList.setNext(listPreviousPointer, leftListCurrentPointer);
                    listPreviousPointer = leftListCurrentPointer;             //update pointers
                    leftListCurrentPointer = IntList.getNext(leftListCurrentPointer);
                    leftCounter++;
                } else if (leftKey >= rightkey && rightCounter < rightListSize) {                                       // leftKey >= rightKey
                    if (firstItem == true) {                      //if item is first in list
                        startOfList = rightListCurrentPointer;        //then right list first element is the first element of list
                        firstItem = false;
                        listPreviousPointer = rightListCurrentPointer;
                        rightListCurrentPointer = IntList.getNext(rightListCurrentPointer);
                        rightCounter++;
                        continue;
                    }
                    IntList.setNext(listPreviousPointer, rightListCurrentPointer);
                    listPreviousPointer = rightListCurrentPointer;             //update pointers
                    rightListCurrentPointer = IntList.getNext(rightListCurrentPointer);
                    rightCounter++;
                }
            }
        }
        return startOfList;
    }
}