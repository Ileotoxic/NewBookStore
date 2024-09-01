package ASM2;

// BookList
class BookList implements AbstractBookList {
    private static final int DEFAULT_CAPACITY = 5;
    private Book[] elements;
    private int size = 0;

    public BookList() {
        elements = new Book[DEFAULT_CAPACITY];
    }

    @Override
    public boolean add(Book book) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        elements[size] = book;
        size++;
        return true;
    }

    @Override
    public Book remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bound: " + index);
        }

        Book oldElement = elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        elements[size] = null;

        if (size > 0 && size < elements.length / 3) {
            resize(elements.length / 2);
        }

        return oldElement;
    }

    @Override
    public Book get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bound: " + index);
        }
        return elements[index];
    }

    @Override
    public Book set(int index, Book book) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bound: " + index);
        }

        Book oldElement = elements[index];
        elements[index] = book;
        return oldElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(Book book) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(book)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Book book) {
        return indexOf(book) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newCapacity) {
        Book[] newElements = new Book[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    @Override
    public String toString() {
        StringBuilder results = new StringBuilder();
        results.append("[");

        for (int i = 0; i < size; i++) {
            results.append(elements[i]);
            if (i < size - 1) {
                results.append(", ");
            }
        }

        results.append("]");
        return results.toString();
    }

    // Bubble sort by ID
    public void sortById() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (elements[j].getId() > elements[j + 1].getId()) {
                    swap(j, j + 1);
                }
            }
        }
    }

    // Bubble sort by title
    public void sortByTitle() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (elements[j].getTitle().compareTo(elements[j + 1].getTitle()) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    // Bubble sort by author
    public void sortByAuthor() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (elements[j].getAuthor().compareTo(elements[j + 1].getAuthor()) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    // Bubble sort by price
    public void sortByPrice() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (elements[j].getPrice() > elements[j + 1].getPrice()) {
                    swap(j, j + 1);
                }
            }
        }
    }

    // Swap elements at indices i and j
    private void swap(int i, int j) {
        Book temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
