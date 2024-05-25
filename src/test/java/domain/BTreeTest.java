package domain;

import domain.list.CircularDoublyLinkedList;
import domain.list.SinglyLinkedList;
import domain.queue.LinkedQueue;
import domain.queue.QueueException;
import domain.stack.LinkedStack;
import domain.stack.StackException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BTreeTest {

    @Test
    void Test() throws TreeException, StackException, QueueException {
        BTree bTree = new BTree();
        SinglyLinkedList singly = new SinglyLinkedList();
        CircularDoublyLinkedList circular = new CircularDoublyLinkedList();
        LinkedStack stack = new LinkedStack();
        LinkedQueue queue = new LinkedQueue();
        BTree alphabetBTree = new BTree();


        for (int i = 0; i < 10; i++) {
            bTree.add(util.Utility.getRandom(50));


        }
//        bTree.add(20);
//        bTree.add(30);
//        bTree.add(40);

        try {
            System.out.println(bTree);//chanced it fo a bit bTree.preOrder()
            System.out.println("Size " + bTree.size());
            int value = util.Utility.getRandom(50);
            bTree.remove(value);
            if (bTree.contains(value)) {
                bTree.remove(value);
                System.out.println("Value: " + value + " has been removed");
            } else
                System.out.println("the value (" + value + ") was not found");
            System.out.println("tree height: " + bTree.height());
            System.out.println(bTree);

        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

        bTree.clear();
        String[] names = {"Zachary", "Lito", "Betzabeth", "Jose David", "Jeaustin", "Eduardo", "Michael", "Katherine", "JP", "Kerlon",
                "Karla", "Veronica", "Aaron", "Jimena", "Prof.Gilbert", "Felipe", "Niguel", "Dennis", "Joseph", "Alexander"};

        String[] countries = {"Argentina", "Chile", "Costa Rica", "Colombia", "Mexico", "Puerto Rico", "Venezuela", "Panama", "Nicaragua", "Honduras"};

        char[] alphabet = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        int num;
        for (int i = 0; i < 20; i++) {
            singly.add(util.Utility.getRandom(20));
            circular.add(names[util.Utility.getRandom(20) - 1]);
            if (i % 2 == 0)
                stack.push(countries[util.Utility.getRandom(10) - 1]);
            for (int j = 0; j < 5; j++) {
                num = util.Utility.getRandom(5000);
                if (num < 1000)
                    num = num + 1000;
                queue.enQueue(num);
            }
        }

        for (char letter : alphabet) {
            alphabetBTree.add(letter);
        }

        bTree.add(singly);
        bTree.add(circular);
        bTree.add(stack);
        bTree.add(queue);
        bTree.add(alphabetBTree);

        bTree.size();
        bTree.height();
        bTree.height(stack);

        bTree.remove(queue);


        System.out.println();


        System.out.println(bTree);


//        try {
//            System.out.println(bTree.InOrder());
//        } catch (TreeException e) {
//            throw new RuntimeException(e);
//        }
    }
}