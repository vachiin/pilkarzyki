package pl.vachiin.app;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Statistics {

    private AtomicInteger count = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> whiteDefense = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Integer> whiteAttack = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Integer> blueDefense = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Integer> blueAttack = new ConcurrentHashMap<>();

    private HashSet<String> allPlayers = new HashSet<>();

    public HashSet<String> getAllPlayers() {
        return allPlayers;
    }

    public void addPercentWhiteDefense(String aPlayer) {
        addToMap(aPlayer, whiteDefense);
    }

    public void increment() {
        count.incrementAndGet();
    }

    public void addPercentWhiteAttack(String aPlayer) {
        addToMap(aPlayer, whiteAttack);
    }

    public void addPercentBlueDefense(String aPlayer) {
        addToMap(aPlayer, blueDefense);
    }

    public void addPercentBlueAttack(String aPlayer) {
        addToMap(aPlayer, blueAttack);
    }

    private void addToMap(String aPlayer, ConcurrentHashMap<String, Integer> aMap) {
        if (!aMap.containsKey(aPlayer)) {
            aMap.put(aPlayer, 0);
        }
        allPlayers.add(aPlayer);
        aMap.put(aPlayer, aMap.get(aPlayer) + 1);
    }

    public Integer getPercentWhiteDefense(String aPlayer) {
        return getIntegerFromDouble(whiteDefense.get(aPlayer));
    }

    public Integer getPercentWhiteAttack(String aPlayer) {
        return getIntegerFromDouble(whiteAttack.get(aPlayer));
    }

    public Integer getPercentBlueDefense(String aPlayer) {
        return getIntegerFromDouble(blueDefense.get(aPlayer));
    }

    public Integer getPercentBlueAttack(String aPlayer) {
        return getIntegerFromDouble(blueAttack.get(aPlayer));
    }

    private Integer getIntegerFromDouble(Integer aInteger) {
        Double hitted = aInteger == null ? 0.0 : Double.valueOf(aInteger);
        return Double.valueOf(hitted * 100.0 / (double) count.get()).intValue();
    }

}
