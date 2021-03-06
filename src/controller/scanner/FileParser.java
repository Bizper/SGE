package controller.scanner;

import impl.action.Spell;
import mapping.*;
import mapping.inside.Block;
import service.AssetManager;
import service.ConceptFactory;
import service.Proc;
import util.Log;

import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static intf.constant.DefaultConstant.DEFAULT_BLOCK_SIZE;
import static intf.constant.DefaultConstant.MAX_MAP_SIZE_HEIGHT;
import static intf.constant.DefaultConstant.MAX_MAP_SIZE_WIDTH;


public class FileParser {

    private static Log log = Log.getInstance(FileParser.class);

    private static double MAX_PROGRESS = 1.0;

    private static double progress;

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
        String cache;
        BufferedReader br = init(file);
        int count = 0, current = 0;
        if(br == null) return sce;
        try {
            while(br.readLine() != null) {
                count++;
            }
            br = init(file);
            while((cache = br.readLine()) != null) {
                setProgress(path, (double)current/(double)count);
                sce = parseSCE(cache, sce);
                current ++;
            }
        } catch (IOException e) {
            log.error(e);
            //Exiter.exit(FileParser.class);
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
        String cache;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader br = init(file);
        if(br == null) return plr;
        try {
            while((cache = br.readLine()) != null) {
                stringBuilder.append(cache).append("\n");
            }
        } catch (IOException e) {
            log.error(e);
        }
        cache = stringBuilder.toString();
        setProgress(path, 0.5);
        plr = parsePLR(cache, plr);
        setProgress(path, MAX_PROGRESS);
        return plr;
    }

    public static MP parseMP(String path) {
        MP mp;
        File file = new File(path);
        if(!path.endsWith(".mp")) {
            log.error("not current MP file. check your file location.");
            return null;
        }
        String name = "";
        Block list[][] = new Block[MAX_MAP_SIZE_WIDTH/DEFAULT_BLOCK_SIZE][MAX_MAP_SIZE_HEIGHT/DEFAULT_BLOCK_SIZE];
        for(int i=0; i<MAX_MAP_SIZE_WIDTH/DEFAULT_BLOCK_SIZE; i++) {
            for(int j=0; j<MAX_MAP_SIZE_HEIGHT/DEFAULT_BLOCK_SIZE; j++) {
                list[i][j] = new Block();
            }
        }
        mp = new MP(name, list);
        String cache;
        BufferedReader br = init(file);
        if(br == null) return mp;
        try {
            while((cache = br.readLine()) != null) {
                mp = parseMP(cache, mp);
            }
        } catch (IOException e) {
            log.error(e);
        }
        setProgress(path, MAX_PROGRESS);
        return mp;
    }

    /**
     * 设置状态
     * @return progress
     */
    public static double getProgress() {
        return progress;
    }

    private static void setProgress(String message, double p) {
        if(p > MAX_PROGRESS) p = MAX_PROGRESS;
        DecimalFormat df = new DecimalFormat("#");
        p = Double.parseDouble(df.format(p * 100));
        //log.log("parsing \"" + message + "\" progress: " + p + "%");
        progress = p;
    }

    private static SCE parseSCE(String str, SCE sce) {
        if(str.isEmpty()) return sce;
        StringBuilder stringBuilder = new StringBuilder();
        char list[] = str.toCharArray();
        boolean isKey = true;
        String key = null, value;
        if(list[0] == '.') {
            key = getInside(stringBuilder, isKey, key, list);
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
                case "mp":
                    sce.addMap(value, value);
                    break;
                case "start":
                    sce.setStart(Integer.parseInt(value));
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

    private static PLR parsePLR(String str, PLR plr) {
        if(str == null || str.isEmpty()) return plr;
        char list[] = str.toCharArray();
        for(int i=0; i<list.length; i++) {
            if(list[i] == '{') {
                String id = str.substring(0, str.indexOf('{') - 1);
                Map<String, String> result = parseInside(str, i);
                str = result.get("release");
                list = str.toCharArray();
                if(id.startsWith("spell")) {
                    Spell spell = ConceptFactory.getInstance(Spell.class);
                    spell.setName(result.get("SpellName"));
                    if(result.get("SpellType").contains("TARGET") && result.get("SpellType").contains("HURT")) {
                        spell.setAction((e, u) -> {
                           e.setMp(e.getMp() - Integer.parseInt(result.get("SpellManaCost")));
                           u.setHp(u.getHp() - Integer.parseInt(result.get("SpellNumber")));
                        });
                    }
                    if(result.get("SpellType").contains("SELF") && result.get("SpellType").contains("HEAL")) {
                        spell.setAction((e) -> {
                            e.setMp(e.getMp() - Integer.parseInt(result.get("SpellManaCost")));
                            e.setHp(e.getHp() + Integer.parseInt(result.get("SpellNumber")));
                        });
                    }
                    Proc.addMappingId(id, spell.getID());
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
                result.put("release", str.substring(i + 2, str.length()));
                break;
            }
            stringBuilder.append(str.charAt(i));
        }
        return result;
    }

    private static MP parseMP(String str, MP mp) {
        if(str == null || str.isEmpty()) return mp;
        StringBuilder stringBuilder = new StringBuilder();
        boolean isKey = true;
        String key = null, value;
        char list[] = str.toCharArray();
        if(list[0] == '.') {
            key = getInside(stringBuilder, isKey, key, list);
            value = stringBuilder.toString();
            if(key == null) {
                return mp;
            }
            switch(key) {
                case "name":
                    mp.setName(value);
                    break;
                case "background":
                    mp.setBackground(AssetManager.getImage(value));
                    break;

            }
        }
        return mp;
    }

    private static String getInside(StringBuilder stringBuilder, boolean isKey, String key, char[] list) {
        for(int i=1; i<list.length; i++) {
            if(list[i] == ' ' && isKey) {
                isKey = false;
                key = stringBuilder.toString();
                stringBuilder.delete(0, stringBuilder.length());
                continue;
            }
            stringBuilder.append(list[i]);
        }
        return key;
    }


}
