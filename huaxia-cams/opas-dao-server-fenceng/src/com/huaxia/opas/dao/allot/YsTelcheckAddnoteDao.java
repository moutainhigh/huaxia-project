package com.huaxia.opas.dao.allot;

import com.huaxia.opas.domain.allot.YsPatchboltInfo;
import com.huaxia.opas.domain.allot.YsTelcheckAddnote;
import java.util.List;
import java.util.Map;

public interface YsTelcheckAddnoteDao {
    int selectCount(Map map);

    List<YsTelcheckAddnote> selectByExample(Map map);
    
    YsTelcheckAddnote selectYsResultInfo(Map map);
    
    YsPatchboltInfo selectYsPatchbolt(Map map);
}