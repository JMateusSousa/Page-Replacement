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
        this.frames = new ArrayList<>();

        //  percorre toda a lista de números das páginas
        for(int i = 0; i < list.size(); i++){
            Inputs numPage = list.get(i);

            //  verifica se a página está carregada em algum frame
            if (!frames.contains(numPage)) {

                // verifica se todos os frames já foram carregados
                // se sim, carrega a página sem ocorrer remoção
                if (frames.size() < numFrames) {
                    frames.add(numPage);
                    numMisses++;
                    continue;
                }
                // se não, remove a primeira página da fila
                else {
                    frames.remove(0);
                    frames.add(numPage);
                    numMisses++;
                }
            }else
                numHits++;
        }
        System.out.print("FIFO: Misses -> " + numMisses + " | Hits -> " + numHits);
        System.out.println(" | Fila -> " + frames);
        return numHits;
    }

    public int MRU(List<Inputs> list){

        boolean aux = false;
        ArrayList<Inputs> queue = new ArrayList<>(numFrames);

        final int base = numFrames - 1;
        final int top = 0;

        //  percorre toda a lista de números das páginas
        for (Inputs input : list) {
            aux = false;

            //  percorre por todos os elementos da fila
            for (Inputs frame : queue) {

                //  verifica se a página i da lista de entrada está carregada
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
                    queue.add(input);
                }

                // se sim, remove o item zero da fila e carrega a página i
                else {
                    numMisses++;
                    queue.remove(0);
                    queue.add(input);
                }
            }
        }

        System.out.print("MRU: Misses -> " + numMisses + " | Hits -> " + numHits);
        System.out.println(" | Fila -> " + queue);
        return numHits;
    }


    public int nru(List<Inputs> list, List<Character> mode, int numReferences){
        this.frames = new ArrayList<>();

        //  percorre toda a lista de números das páginas
        for(int i = 0; i < list.size(); i++) {
            // verifica se chegou na zerésima página
            if (i % numReferences == 0) {
                for (Inputs frame : frames) {
                    frame.setBitR(false);
                }
            }

            Inputs numPage = list.get(i);
            boolean found = false;
            //  verifica se é operação de escrita
            if (mode.get(i) == 'W') {
                numPage.setBitM(true);
            }


            //  verifica se a página está carregada em algum frame
            for (int j = 0; j < frames.size(); j++) {
                if ((frames.get(j).getValor() == numPage.getValor())) {
                    found = true;
                    break;
                }
            }

            //  se não estiver carregado
            if (!found) {
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
                    Inputs frameOut = frames.get(0);
                    int index = 0;
                    for (Inputs frame : frames) {
                        if (frame.getClasse() < frameOut.getClasse()) {
                            index = frames.indexOf(frame);
                        }
                    }
                    frames.remove(index);
                    frames.add(numPage);
                    numMisses++;
                }
                // se a página já estiver carregada
            }
            else
                numHits++;
        }
        System.out.print("NRU: Misses -> " + numMisses + " | Hits -> " + numHits);
        System.out.println(" | Fila -> " + frames);
        return numHits;
    }

    public int optimum (List<Inputs> list) {

        int max_index = 0,  j = 0, lenghList = 0, frame_index = 0;

        this.frames = new ArrayList<>();
        lenghList = list.size();
        for (int i = 0; i < lenghList; i++) {

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
                // página que será usada mais tardiamente referenciada
                else{
                    max_index = -1;
                    for(j = 0; j < frames.size(); j++){
                        int indexPage = list.indexOf(frames.get(j));
                        if(indexPage == -1){
                            frame_index = j;
                            break;
                        }
                        if(indexPage > max_index){
                            frame_index = j;
                            max_index = indexPage;
                        }
                    }
                    frames.set(frame_index, numPage);
                    numMisses++;
                }

            } else
                numHits++;
        }
        System.out.print("OPT: Misses -> " + numMisses + " | Hits -> " + numHits);
        System.out.println(" | Frames -> " + frames);
        return numHits;
    }




    public int secondChance(List<Inputs> list, int numReferences) {
        this.frames = new ArrayList<>();

        //  percorre toda a lista de números das páginas
        for(int i = 0; i < list.size(); i++) {
            // verifica se chegou na zerésima página
            if(i % numReferences == 0){
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
                        //  verifica se o página contém bitR igual a 1
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
                frames.get(j).setBitR(true);
            }
        }
        System.out.print("Segunda Chance: Misses -> " + numMisses + " | Hits -> " + numHits);
        System.out.println(" | Fila -> " + frames);
        return numHits;
    }

}
