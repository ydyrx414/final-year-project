package com.example.springboot.service;

import cn.hutool.core.lang.Tuple;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.dto.CorporationQueryDTO;
import com.example.springboot.dto.UpdateCorpDTO;
import com.example.springboot.entity.Corporation;
import com.example.springboot.dto.CorporationRegisterDTO;
import com.example.springboot.entity.User;
import com.example.springboot.entity.VCorp;
import com.example.springboot.utils.Tuple2;
import com.example.springboot.vo.PageVO;

public interface ICorporationService {
    VCorp update(UpdateCorpDTO dto);

    boolean saveCorporation(Corporation corporation);

    PageVO<VCorp> queryCorporations(CorporationQueryDTO dto);
}
