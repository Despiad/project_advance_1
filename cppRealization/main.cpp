#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <chrono>

using std::vector;

struct Node {
    int data;
    int rank;

    Node *leftChild;
    Node *rightChild;

    explicit Node(int element, Node *left = nullptr, Node *right = nullptr) {
        data = element;
        rank = 0;
        leftChild = left;
        rightChild = right;
    }
};

class LeftistHeap {
private:
    Node *root;

    void swapChildren(Node *parent) {
        Node *tmp = parent->leftChild;
        parent->leftChild = parent->rightChild;
        parent->rightChild = tmp;
    }

public:
    LeftistHeap() {
        root = nullptr;
    }

    bool isEmpty() {
        return (root == nullptr);
    }

    Node *leftist_heap(Node *left, Node *right) {
        if (left->leftChild == nullptr) {
            left->leftChild = right;
        } else {
            left->rightChild = merge(left->rightChild, right);

            if (left->leftChild->rank < left->rightChild->rank) {
                swapChildren(left);
            }

            left->rank = left->rightChild->rank + 1;
        }
        return left;
    }

    Node *merge(Node *left, Node *right) {

        if (left == nullptr) {
            return right;
        }

        if (right == nullptr) {
            return left;
        }

        if (left->data < right->data) {
            return leftist_heap(left, right);
        }

        return (leftist_heap(right, left));
    }

    void insert(int value) {
        root = merge(new Node(value), root);
    }

    int extractMin() {
        int minElement = root->data;
        Node *oldRoot = root;
        root = merge(root->leftChild, root->rightChild);
        delete oldRoot;
        return minElement;
    }
};


void insertTime(LeftistHeap &heap, std::ostream &output = std::cout) {
    const unsigned int size = 1000000;
    output << "size,time\n";
    for (unsigned int index = 0; index < size; ++index) {
        std::chrono::high_resolution_clock::time_point start = std::chrono::high_resolution_clock::now();
        heap.insert(index);
        std::chrono::high_resolution_clock::time_point finish = std::chrono::high_resolution_clock::now();
        auto duration = std::chrono::duration_cast<std::chrono::nanoseconds>(finish - start).count();
        if (duration <= 1000 && index % 100 == 0) {
            output << index << ',' << duration << '\n';
        }
    }
    std::cout << heap.isEmpty() << "\n";
}

void extractTime(LeftistHeap &heap, std::ostream &output = std::cout) {
    const unsigned int size = 1000000;
    output << "size,time\n";
    for (unsigned int index = size; index > 0; --index) {
        std::chrono::high_resolution_clock::time_point start = std::chrono::high_resolution_clock::now();
        heap.extractMin();
        std::chrono::high_resolution_clock::time_point finish = std::chrono::high_resolution_clock::now();
        auto duration = std::chrono::duration_cast<std::chrono::nanoseconds>(finish - start).count();

        if (duration <= 8000 && index % 10 == 0) {
            output << index << ',' << duration << '\n';
        }
    }
    std::cout << heap.isEmpty() << "\n";
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::ofstream foutInsert("insertTime.csv");
    std::ofstream foutExtract("extractTime.csv");
    LeftistHeap heap;
    insertTime(heap, foutInsert);
    extractTime(heap, foutExtract);
    foutInsert.close();
    foutExtract.close();
    return 0;
}
