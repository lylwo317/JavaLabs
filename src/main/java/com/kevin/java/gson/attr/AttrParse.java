package com.kevin.java.gson.attr;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.*;

public class AttrParse {

    static final String ruleJson = "[{\"attr\":\"ptz_cruise_enable\",\"on\":[{\"attr\":\"ptz_cruise_enable\",\"value\":\"1\"},{\"attr\":\"pets_track_enable\",\"value\":\"0\"},{\"attr\":\"sound_track_enable\",\"value\":\"0\"},{\"attr\":\"humans_track_enable\",\"value\":\"0\"}],\"off\":[{\"attr\":\"ptz_cruise_enable\",\"value\":\"0\"}]},{\"attr\":\"pets_track_enable\",\"on\":[{\"attr\":\"pets_track_enable\",\"value\":\"1\"},{\"attr\":\"pets_detect_enable\",\"value\":\"1\"},{\"attr\":\"ptz_cruise_enable\",\"value\":\"0\"},{\"attr\":\"sound_track_enable\",\"value\":\"0\"},{\"attr\":\"humans_track_enable\",\"value\":\"0\"}],\"off\":[{\"attr\":\"pets_track_enable\",\"value\":\"0\"}]},{\"attr\":\"sound_track_enable\",\"on\":[{\"attr\":\"sound_track_enable\",\"value\":\"1\"},{\"attr\":\"pets_track_enable\",\"value\":\"0\"},{\"attr\":\"ptz_cruise_enable\",\"value\":\"0\"},{\"attr\":\"humans_track_enable\",\"value\":\"0\"}],\"off\":[{\"attr\":\"sound_track_enable\",\"value\":\"0\"}]},{\"attr\":\"humans_track_enable\",\"on\":[{\"attr\":\"humans_track_enable\",\"value\":\"1\"},{\"attr\":\"human_detect_enable\",\"value\":\"1\"},{\"attr\":\"pets_track_enable\",\"value\":\"0\"},{\"attr\":\"sound_track_enable\",\"value\":\"0\"},{\"attr\":\"ptz_cruise_enable\",\"value\":\"0\"}],\"off\":[{\"attr\":\"humans_track_enable\",\"value\":\"0\"}]},{\"attr\":\"gesture_detect_enable\",\"on\":[{\"attr\":\"gesture_detect_enable\",\"value\":\"1\"},{\"attr\":\"face_detect_enable\",\"value\":\"0\"},{\"attr\":\"human_detect_enable\",\"value\":\"0\"},{\"attr\":\"pets_detect_enable\",\"value\":\"0\"},{\"attr\":\"mdtrigger_enable\",\"value\":\"0\"},{\"attr\":\"soundtrigger\",\"value\":\"0\"},{\"attr\":\"ptz_cruise_enable\",\"value\":\"0\"},{\"attr\":\"pets_track_enable\",\"value\":\"0\"},{\"attr\":\"sound_track_enable\",\"value\":\"0\"},{\"attr\":\"humans_track_enable\",\"value\":\"0\"}],\"off\":[{\"attr\":\"gesture_detect_enable\",\"value\":\"0\"}]},{\"attr\":\"mdtrigger_enable\",\"on\":[{\"attr\":\"mdtrigger_enable\",\"value\":\"1\"},{\"attr\":\"gesture_detect_enable\",\"value\":\"0\"}],\"off\":[{\"attr\":\"mdtrigger_enable\",\"value\":\"0\"}]},{\"attr\":\"soundtrigger_enable\",\"on\":[{\"attr\":\"soundtrigger_enable\",\"value\":\"1\"},{\"attr\":\"gesture_detect_enable\",\"value\":\"0\"}],\"off\":[{\"attr\":\"soundtrigger_enable\",\"value\":\"0\"}]},{\"attr\":\"human_detect_enable\",\"on\":[{\"attr\":\"human_detect_enable\",\"value\":\"1\"},{\"attr\":\"gesture_detect_enable\",\"value\":\"0\"}],\"off\":[{\"attr\":\"human_detect_enable\",\"value\":\"0\"},{\"attr\":\"humans_track_enable\",\"value\":\"0\"}]},{\"attr\":\"face_detect_enable\",\"on\":[{\"attr\":\"face_detect_enable\",\"value\":\"1\"},{\"attr\":\"gesture_detect_enable\",\"value\":\"0\"}],\"off\":[{\"attr\":\"face_detect_enable\",\"value\":\"0\"}]},{\"attr\":\"pets_detect_enable\",\"on\":[{\"attr\":\"pets_detect_enable\",\"value\":\"1\"},{\"attr\":\"gesture_detect_enable\",\"value\":\"0\"}],\"off\":[{\"attr\":\"pets_detect_enable\",\"value\":\"0\"},{\"attr\":\"pets_track_enable\",\"value\":\"0\"}]}]";

    public static void main(String[] args) {

        Gson gson = new Gson();
        List<RuleEntity> ruleEntityList = gson.fromJson(ruleJson, new TypeToken<List<RuleEntity>>() {
        }.getType());

        Map<String, String> currentAttrValueMap = new HashMap<>();
        currentAttrValueMap.put("ptz_cruise_enable", "0");
        currentAttrValueMap.put("pets_track_enable", "0");
        currentAttrValueMap.put("sound_track_enable", "0");
        currentAttrValueMap.put("humans_track_enable", "1");
        currentAttrValueMap.put("gesture_detect_enable", "0");
        currentAttrValueMap.put("mdtrigger_enable", "1");
        currentAttrValueMap.put("soundtrigger_enable", "1");
        currentAttrValueMap.put("human_detect_enable", "1");
        currentAttrValueMap.put("face_detect_enable", "1");
        currentAttrValueMap.put("pets_detect_enable", "1");


//        if (hasParseAttr.contains("mdtrigger_enable")) {
//            System.out.println("包含");
//        }

//        String changeAttr = "ptz_cruise_enable";
//        String changeValue = "1";
        String changeAttr = "pets_track_enable";
        String changeValue = "1";


        Map<String, RuleEntity> ruleEntityMap = new HashMap<>();
        for (RuleEntity ruleEntity : ruleEntityList) {
            ruleEntityMap.put(ruleEntity.getAttr(), ruleEntity);
        }

        RuleEntity ruleEntity = ruleEntityMap.get(changeAttr);
        List<RuleEntity.AttrEntity> attrEntityList;
        if (changeValue.equals("1")) {
            attrEntityList = ruleEntity.getOn();
        } else {
            attrEntityList = ruleEntity.getOff();
        }

        Queue<List<RuleEntity.AttrEntity>> attrEntityListQueue = new LinkedList<>();
        attrEntityListQueue.offer(attrEntityList);


        List<RuleEntity.AttrEntity> result = new ArrayList<>();
        Set<String> hasParseAttr = new HashSet<>();

        while (!attrEntityListQueue.isEmpty()) {//层序遍历
            List<RuleEntity.AttrEntity> list = attrEntityListQueue.poll();
            for (RuleEntity.AttrEntity attrEntity : list) {
                if (!hasParseAttr.contains(attrEntity.getAttr())) {//遍历关联或者互斥的值
                    //添加到队列,做层序遍历。将所有关联或者互斥的资源值加入进来，得出开启或者关闭这个开关需要确保的其它所有开关的状态
//                    if (!attrEntity.getValue().equals(currentAttrValueMap.get(attrEntity.getAttr()))) {//不等于，说明这个关联或者互斥值需要修改
//                    }
                    result.add(attrEntity);//关联或者互斥的资源值
                    RuleEntity next = ruleEntityMap.get(attrEntity.getAttr());
                    if (attrEntity.getValue().equals("1")) {
                        attrEntityListQueue.offer(next.getOn());
                    } else {
                        attrEntityListQueue.offer(next.getOff());
                    }
                    hasParseAttr.add(attrEntity.getAttr());
                }
            }
        }


        System.out.println(result);
    }

}
