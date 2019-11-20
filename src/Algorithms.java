import java.util.ArrayList;
import java.util.List;

public class Algorithms {

    private int numMisses;
    private int numHits;
    private int numFrames;
    ArrayList<Inputs> frames;

    public Algorithms(int numFrames){

        this.numFrames = numFrames;
        numMisses = 0;
        numHits = 0;
    }


    public int FIFO(List<Inputs> list){
        int insert = 0;
        this.frames = new ArrayList<>();

        //  percorre toda a list de números das páginas
        for(int i = 0; i < list.size(); i++){
            Inputs numPage = list.get(i);

            //  verifica se a página está carregada em algum frame
            if (!frames.contains(numPage)) {

                // verifica se todos os frames já foram carregados
                // se não, carrega a página sem ocorrer remoção
                if (frames.size() < numFrames) {
                    frames.add(numPage);
                    numMisses++;
                    continue;
                }
                // se não, remove a primeira página da fila
                else {
                    frames.remove(insert);
                    frames.add(insert, numPage);
                    insert++;
                    numMisses++;
                    if (insert == numFrames) {
                        insert = 0;
                    }
                }
            }else
                numHits++;
        }
        System.out.print("FIFO: Misses -> " + numMisses + " | Hits -> " + numHits);
        System.out.println(" | Frames -> " + frames);
        return numHits;
    }

    public int MRU(List<Inputs> list){

        boolean aux = false;
        ArrayList<Inputs> queue = new ArrayList<>(numFrames);

        final int base = numFrames - 1;
        final int top = 0;

        //  percorre toda a list de números das páginas
        for (Inputs input : list) {
            aux = false;

            //  percorre por todos os elementos da fila
            for (Inputs frame : queue) {

                //  verifica se a página i da list de entrada está carregada
                //  se sim, coloca a página j no final da fila
                if (frame.getValor() == input.getValor()){
                    queue.remove(frame);
                    //queue.add(top,input);
                    queue.add(input);
                    aux = true;
                    numHits++;
                    break;
                }
            }

            //  se a página não estiver carregada
            if(!aux){
                // verifica se todos os frames estão carregados
                // se não, carrega a página i
                if (queue.size() != numFrames){
                    numMisses++;
                    //queue.add(top,input);
                    queue.add(input);
                }

                // se sim, remove o item zero da fila e carrega a página i
                else {
                    numMisses++;
                    //queue.remove(base);
                    //queue.add(top,input);
                    queue.remove(0);
                    queue.add(input);
                }
            }
        }

        System.out.print("MRU: Misses -> " + numMisses + " | Hits -> " + numHits);
        System.out.println(" | Fila -> " + queue);
        return numHits;
    }


    /*public void nru(BufferedReader br) throws IOException {
        Page[] table = new Page[numframes];
        int count = 0;

        String st;
        while ((st = br.readLine()) != null) {

            String addr = st.substring(0, 5);
            String action = st.substring(9, 10);

            if(count < numframes) {
                boolean nofault = false;
                for(int i = 0; i < count; i++) {
                    if(table[i].addr.equals(addr)) {
                        nofault = true;
                        System.out.println("hit");
                        if(action.equals("W"))
                            table[i].dirty = 1;
                        else
                            table[i].bit = 1;
                    }
                }
                if(!nofault) {
                    if(action.equals("W")) {
                        table[count] = new Page(addr, 1, 1);
                        System.out.println("page fault - evict dirty");
                    }
                    else {
                        table[count] = new Page(addr, 1, 0);
                        System.out.println("page fault - evict clean");
                    }
                    count++;

                    pagefaults++;
                }
            }

            else {
                boolean nofault = false;
                for(Page p : table) {
                    if(p.addr.equals(addr)) {
                        System.out.println("hit");
                        nofault = true;
                        if(action.equals("W"))
                            p.dirty = 1;
                        else
                            p.bit = 1;
                        break;
                    }

                }

                if(!nofault) {
                    boolean replaced = false;
                    int second = -1;
                    int third = -1;
                    for(int i = 0; i < table.length; i++) {
                        if(table[i].bit == 0 && table[i].dirty == 0) {
                            if(action.equals("W"))
                                table[i] = new Page(addr, 1, 1);
                            else
                                table[i] = new Page(addr, 1, 0);
                            replaced = true;
                            System.out.println("page fault - evict clean");
                            pagefaults++;
                            break;
                        }
                        else if(table[i].bit == 0 && table[i].dirty == 1)
                            second = i;

                        else if(table[i].bit == 1 && table[i].dirty == 0)
                            third = i;
                    }
                    if(!replaced) {
                        int index;

                        if(second > -1) {
                            index = second;
                            System.out.println("page fault - evict dirty");
                        }
                        else if(third > -1) {
                            index = third;
                            System.out.println("page fault - evict clean");
                        }
                        else {
                            index = 0;
                            System.out.println("page fault - evict dirty");

                        }

                        if(action.equals("W"))
                            table[index] = new Page(addr, 1, 1);
                        else
                            table[index] = new Page(addr, 1, 0);

                        pagefaults++;
                    }
                }
            }

            if(action.equals("W"))
                writes++;
            memaccess++;

            if(refresh % memaccess == 0) {
                for(int i = 0; i < count; i++) {
                    table[i].bit = 0;
                }
            }
        }
    }*/

    public int optimum (List<Inputs> list) {

        int max_index = 0;
        int j = 0;
        int frame_index = 0;

        this.frames = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            Inputs numPage = list.get(0);
            list.remove(0);

            //  verifica se a página está carregada em algum frame
            if (!frames.contains(numPage)) {

                // verifica se todos os frames já foram carregados
                // se não, carrega a página sem ocorrer remoção
                if (frames.size() < numFrames) {
                    frames.add(numPage);
                    numMisses++;
                    continue;
                }

                // se sim, libera o frame que contém a
                // página que será usada mais tardiamente
                else{
                    max_index = -1;
                    for(j = 0; j < frames.size(); j++){
                        if(list.indexOf(frames.get(j)) == -1){
                            frame_index = j;
                            break;
                        }
                        if(list.indexOf(frames.get(j)) > max_index){
                            frame_index = j;
                            max_index = list.indexOf(frames.get(j));
                        }
                    }
                    frames.set(frame_index, numPage);
                    numMisses++;
                }
                // se sim, remove a primeira página da fila
            } else
                numHits++;
        }
        System.out.print("OPT: Misses -> " + numMisses + " | Hits -> " + numHits);
        System.out.println(" | Frames -> " + frames);
        return numHits;
    }

    public int secondChance(List<Inputs> list, int numReferences) {
        this.frames = new ArrayList<>();

        //  percorre toda a list de números das páginas
        for(int i = 0; i < list.size(); i++) {
            if(list.size() % numReferences == 0){
                for(Inputs frame: frames){
                    frame.setBitR(false);
                }
            }
            Inputs numPage = list.get(i);
            boolean found = false;
            int j;

            //  verifica se a página está carregada em algum frame
            for (j = 0; j < frames.size(); j++) {
                if ((frames.get(j).getValor() == numPage.getValor())) {
                    found = true;
                    break;
                }
            }

            //  se não estiver carregado
            if(!found){
                // verifica se todos os frames já foram carregados
                // se sim, carrega a página sem ocorrer remoção
                if (frames.size() < numFrames) {
                    frames.add(numPage);
                    numMisses++;
                    continue;
                }
                //  se não, remove a primeira página da fila
                //  que tiver o bitR igual a zero
                else {
                    for (int k = 0; k < numFrames; k++) {
                        if(frames.get(k).isBitR() == true) {
                            Inputs frame = frames.get(k);
                            frame.setBitR(false);
                            frames.remove(k);
                            frames.add(frame);
                        }else {
                            frames.remove(k);
                            frames.add(numPage);
                            numMisses++;
                            break;
                        }
                    }
                }
            }
            // se a página já estiver carregada
            else {
                numHits++;
                Inputs foundFrame = frames.get(j);
                foundFrame.setBitR(true);
                frames.remove(j);
                frames.add(foundFrame);
            }
        }
        System.out.print("Segunda Chance: Misses -> " + numMisses + " | Hits -> " + numHits);
        System.out.println(" | Fila -> " + frames);
        return numHits;
    }

}
