//Dorian Rzasa
#include <iostream>

class Node {
public:
    int key;
    char value;
    Node* left;
    Node* right;
    int height;

    Node(int k, char v) : key(k), value(v), left(nullptr), right(nullptr), height(1) {}
};

class AVLDictionary {
private:
    Node* root;

    int getHeight(Node* N) {
        return (N == nullptr) ? 0 : N->height;
    }

    int getMax(int a, int b) {
        return (a > b) ? a : b;
    }

    Node* rightRotate(Node* y) {
        Node* x = y->left;
        Node* T2 = x->right;

        x->right = y;
        y->left = T2;

        y->height = getMax(getHeight(y->left), getHeight(y->right)) + 1;
        x->height = getMax(getHeight(x->left), getHeight(x->right)) + 1;

        return x;
    }

    Node* leftRotate(Node* x) {
        Node* y = x->right;
        Node* T2 = y->left;

        y->left = x;
        x->right = T2;

        x->height = getMax(getHeight(x->left), getHeight(x->right)) + 1;
        y->height = getMax(getHeight(y->left), getHeight(y->right)) + 1;

        return y;
    }

    int getBalance(Node* N) {
        return (N == nullptr) ? 0 : getHeight(N->left) - getHeight(N->right);
    }

    Node* insert(Node* node, int key, char value) {
        if (node == nullptr)
            return new Node(key, value);

        if (key < node->key)
            node->left = insert(node->left, key, value);
        else if (key > node->key)
            node->right = insert(node->right, key, value);
        else
            return node;

        node->height = 1 + getMax(getHeight(node->left), getHeight(node->right));

        int balance = getBalance(node);

        if (balance > 1 && key < node->left->key)
            return rightRotate(node);

        if (balance < -1 && key > node->right->key)
            return leftRotate(node);

        if (balance > 1 && key > node->left->key) {
            node->left = leftRotate(node->left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node->right->key) {
            node->right = rightRotate(node->right);
            return leftRotate(node);
        }

        return node;
    }

    Node* findMin(Node* node) {
        Node* current = node;
        while (current->left != nullptr)
            current = current->left;
        return current;
    }

    Node* deleteNode(Node* root, int key) {
        if (root == nullptr)
            return root;

        if (key < root->key)
            root->left = deleteNode(root->left, key);
        else if (key > root->key)
            root->right = deleteNode(root->right, key);
        else {
            if ((root->left == nullptr) || (root->right == nullptr)) {
                Node* temp = root->left ? root->left : root->right;

                if (temp == nullptr) {
                    temp = root;
                    root = nullptr;
                } else {
                    *root = *temp;
                    delete temp;
                }
            } else {
                Node* temp = findMin(root->right);
                root->key = temp->key;
                root->value = temp->value;
                root->right = deleteNode(root->right, temp->key);
            }
        }

        if (root == nullptr)
            return root;

        root->height = 1 + getMax(getHeight(root->left), getHeight(root->right));

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root->left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root->left) < 0) {
            root->left = leftRotate(root->left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root->right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root->right) > 0) {
            root->right = rightRotate(root->right);
            return leftRotate(root);
        }

        return root;
    }

    Node* find(Node* node, int key) {
        if (node == nullptr || node->key == key) {
            return node;
        }

        if (key < node->key) {
            return find(node->left, key);
        }

        return find(node->right, key);
    }

    void inOrderKeys(Node* node) {
        if (node != nullptr) {
            inOrderKeys(node->left);
            std::cout << node->key << " ";
            inOrderKeys(node->right);
        }
    }

    void inOrderValues(Node* node) {
        if (node != nullptr) {
            inOrderValues(node->left);
            std::cout << node->value << " ";
            inOrderValues(node->right);
        }
    }

public:
    AVLDictionary() : root(nullptr) {}

    void insert(int key, char value) {
        root = insert(root, key, value);
    }

    void remove(int key) {
        root = deleteNode(root, key);
    }

    void replace(int key, char value) {
        Node* existingNode = find(root, key);
        if (existingNode != nullptr) {
            existingNode->value = value;
        }
    }

    char find(int key) {
        Node* node = find(root, key);
        return (node != nullptr) ? node->value : '\0';
    }

    void printKeys() {
        inOrderKeys(root);
        std::cout << std::endl;
    }

    void printValues() {
        inOrderValues(root);
        std::cout << std::endl;
    }
};

int main() {

    int M;
    std::cin >> M;

    for (int m = 0; m < M; ++m) {
        AVLDictionary dictionary;

        int Ni;
        std::cin >> Ni;

        for (int i = 0; i < Ni; ++i) {
            char operation;
            std::cin >> operation;

            switch (operation) {
                case 'K':
                    dictionary.printKeys();
                    break;
                case 'V':
                    dictionary.printValues();
                    break;
                case 'I': {
                    int key;
                    char value;
                    std::cin >> key >> value;
                    dictionary.insert(key, value);
                    break;
                }
                case 'D': {
                    int key;
                    std::cin >> key;
                    dictionary.remove(key);
                    break;
                }
                case 'R': {
                    int key;
                    char value;
                    std::cin >> key >> value;
                    dictionary.replace(key, value);
                    break;
                }
                case 'F': {
                    int key;
                    std::cin >> key;
                    char result = dictionary.find(key);
                    if (result != '\0') {
                        std::cout << result << std::endl;
                    } else {
                        std::cout << "BRAK" << std::endl;
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

    return 0;
}