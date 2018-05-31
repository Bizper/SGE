package controller.scanner;

import constant.DefaultConstant;
import impl.action.Spell;
import mapping.*;
import mapping.inside.Block;
import service.ConceptFactory;
import service.Proc;
import util.Log;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileParser {

    private static Log log = Log.getInstance(FileParser.class);

    private static double MIN_PROGRESS = 0.0;
    private static double MAX_PROGRESS = 100.0;

    private static BufferedReader br;

    private static double progress = MIN_PROGRESS;

    public static BufferedReader init(File f) {
        try {
            return new BufferedReader(new FileReader(f));
        } catch (IOException e) {
            log.error(e);
        }
        return null;
    }

    public static SCE parseSCE(String path) {
        SCE sce = new SCE();
        File file = new File(path);
        if(!path.endsWith(".sce")) {
            log.error("not current SCE file. check your file location.");
            return null;
        }
        setProgress(path, MIN_PROGRESS);
        String cache;
        br = init(file);
        if(br == null) return sce;
        try {
            while((cache = br.readLine()) != null) {
                sce = parseSCE(cache, sce);
            }
        } catch (IOException e) {
            log.error(e);
        }
        setProgress(path, MAX_PROGRESS);
        return sce;
    }

    public static PLR parsePLR(String path) {
        PLR plr = new PLR();
        File file = new File(path);
        if(!path.endsWith(".plr")) {
            log.error("not current PLR file. check your file location.");
            return null;
        }
        setProgress(path, MIN_PROGRESS);
        String cache = "";
        StringBuilder stringBuilder = new StringBuilder();
        br = init(file);
        if(br == null) return plr;
        try {
            while((cache = br.readLine()) != null) {
                stringBuilder.append(cache).append("\n");
            }
        } catch (IOException e) {
            log.error(e);
        }
        cache = stringBuilder.toString();
        setProgress(path, 50.0);
        plr = parsePLR(cache, plr);
        setProgress(path, MAX_PROGRESS);
        return plr;
    }

    public static MP parseMP(String path) {
        MP mp = null;
        if(!path.endsWith(".mp")) {
            log.error("not current MP file. check your file location.");
            return null;
        }
        setProgress(path, MIN_PROGRESS);
        String name = "";
        Block list[][] = new Block[DefaultConstant.MAX_MAP_SIZE][DefaultConstant.MAX_MAP_SIZE];
        mp = new MP(name, list);
        setProgress(path, MAX_PROGRESS);
        return mp;
    }

    /**
     * 设置状态
     * @return
     */
    public static double getProgress() {
        return progress;
    }

    private static void setProgress(String message, double p) {
        if(p > MAX_PROGRESS) p = MAX_PROGRESS;
        log.log("parsing \"" + message + "\" progress: " + p + "%");
        progress = p;
    }

    private static SCE parseSCE(String str, SCE sce) {
        if(str.isEmpty()) return sce;
        StringBuilder stringBuilder = new StringBuilder();
        char list[] = str.toCharArray();
        boolean isKey = true;
        String key = null, value = null;
        if(list[0] == '.') {
            for(int i=1; i<list.length; i++) {
                if(list[i] == ' ' && isKey) {
                    isKey = false;
                    key = stringBuilder.toString();
                    stringBuilder.delete(0, stringBuilder.length());
                    continue;
                }
                stringBuilder.append(list[i]);
            }
            value = stringBuilder.toString();
            if(key == null) {
                return sce;
            }
            switch(key) {
                case "name":
                    sce.setName(value);
                    break;
                case "version":
                    sce.setVersion(Double.parseDouble(value));
                    break;
                case "main":
                    sce.setMain(Boolean.getBoolean(value));
                    break;
            }
            return sce;
        }
        if(list[0] == '[') {
            for(int i=1; i<list.length; i++) {
                if(list[i] == ']' && isKey) {
                    isKey = false;
                    key = stringBuilder.toString();
                    stringBuilder.delete(0, stringBuilder.length());
                    continue;
                }
                stringBuilder.append(list[i]);
            }
            if(key == null) {
                return sce;
            }
            value = stringBuilder.toString();
            sce.addWords(Integer.parseInt(key), value);
        }
        return sce;
    }

    private static boolean flag = false;

    private static PLR parsePLR(String str, PLR plr) {
        if(str == null || str.isEmpty()) return plr;
        char list[] = str.toCharArray();
        for(int i=0; i<list.length; i++) {
            if(list[i] == '{') {
                String id = str.substring(0, str.indexOf('{') - 1);
                Map<String, String> result = parseInside(str, i);
                if(id.startsWith("spell")) {
                    Spell spell = ConceptFactory.getInstance(Spell.class);
                    if(result.get("SpellType").contains("TARGET")) {
                        spell.setName(result.get("SpellName"));
                        spell.setAction((e, u) -> {
                           e.setMp(e.getMp() - Integer.parseInt(result.get("SpellManaCost")));
                           u.setHp(u.getHp() - Integer.parseInt(result.get("SpellNumber")));
                        });
                    }
                    Proc.add(id, spell.getID());
                }

            }
        }
        return plr;
    }

    private static Map<String, String> parseInside(String str, int pointer) {
        Map<String, String> result = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        int offset = str.charAt(pointer + 1) == '\n' ? 2 : 1;
        for(int i=pointer + offset; i<str.length(); i++) {
            if(str.charAt(i) == '}') {
                String st = stringBuilder.toString();
                String r[] = st.split("\n");
                for(int j=0; j<r.length; j++) {
                    String s = r[j];
                    String res[] = s.split("=");
                    result.put(res[0], res[1]);
                }
                break;
            }
            stringBuilder.append(str.charAt(i));
        }
        return result;
    }

}
