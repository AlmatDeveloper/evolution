package kz.evo.p8_concurrent_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

// для работы в многопоточном режиме есть специальные коллекции
// их методы синхронизированы
public class SyncCollectionsExampleClass {
    public static void main(String[] args) {
        // при записи в ConcurrentHashMap, блокируется не вся таблица,
        // блокируется только ячейка в которую идет запись
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

        // есть возможность завернуть классические коллекции в синхронизированные
        Collections.synchronizedList(new ArrayList<>());
        // такой завернутый Map будет проигрывать concurrentHashMap
        Collections.synchronizedMap(new HashMap<>());

        // Чтобы поток мог работать с таким листом каким он видел его в начале
        // если другие потоки что-то меняют в листе,
        // то создается новый лист и ссылка листа меняется
        // при этом первый поток будет видеть старую версию массива
        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();

        // когда мы хотим чтобы один поток производил данные, а другой считывал
        // producer складывает данные, consumer считывает данные
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10); // емкость
        // при переполнении емкости методы работают по разному
//        arrayBlockingQueue.put(); // поток который хотел добавить уйдет в ожидание если емкость переполнена
//        arrayBlockingQueue.offer(); // вернет true или false при попытке добавить новую запись
//        arrayBlockingQueue.add(); // если емкость переполнена просто бросит ошибку

        // consumer при получении данных удаляет запись
//        arrayBlockingQueue.take(); // если очередь пустая будет ждать
//        arrayBlockingQueue.poll(); // если очередь пустая вернет null
//        arrayBlockingQueue.peek(); // при получении записи не удаляет ее
    }
}
