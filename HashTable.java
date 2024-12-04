import java.util.LinkedList; // Importa LinkedList


class HashTable<K, V> {
    private LinkedList<Entry<K, V>>[] table;
    private int size;
    private int capacity;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    // Clase anidada Entry
    static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + ": " + value + "}";
        }
    }

    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity) {
        this.capacity = nextPrime(initialCapacity);
        this.table = new LinkedList[this.capacity];
        this.size = 0;
        for (int i = 0; i < this.capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void insert(K key, V value) {
        int index = hash(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value; // Actualiza el valor si ya existe
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;
        if (getLoadFactor() > LOAD_FACTOR_THRESHOLD) {
            rehash(true);
        }
    }

    public void remove(K key) {
        int index = hash(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                table[index].remove(entry);
                size--;
                return;
            }
        }
    }

    public V get(K key) {
        int index = hash(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public double getLoadFactor() {
        return (double) size / capacity;
    }

    @SuppressWarnings("unchecked")
    private void rehash(boolean expand) {
        int newCapacity = expand ? nextPrime(capacity * 2) : nextPrime(capacity / 2);
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[newCapacity];
        capacity = newCapacity;
        size = 0;
        for (int i = 0; i < newCapacity; i++) {
            table[i] = new LinkedList<>();
        }
        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            for (Entry<K, V> entry : bucket) {
                insert(entry.key, entry.value);
            }
        }
    }

    public LinkedList<Entry<K, V>> getBucket(int index) {
        if (index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return table[index];
    }

    public int getCapacity() {
        return capacity;
    }

    private int nextPrime(int num) {
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }
}

class Producto {
    private double precio;

    public Producto(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{precio=" + precio + '}';
    }
}

