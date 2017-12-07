$(function () {
    from_btns();
    reset_btns();
    select_menu_jueceren();//决策人
    select_menu_jcrzw();//决策人职位
    select_menu_gdjcr();//是否搞定决策人
    select_menu_gdjcrzw();//搞定决策人的职位
    select_menu_qs();//确有其事详情
    select_menu_qyqsbz();//意外事情详情
    select_menu_sycp();//5人以上使用产品效果
    select_menu_sycpbz();//未满足5人以上效果
    select_menu_jcrdz();//下一步决策人动作
    select_menu_xqdz();//下一步需求动作
});

var juecerenObj = {"0": "否", "1": "是"};//决策人

var juecerenzhiweiObj = { //决策人职位
    "BOSS": "老板",
    "COMPANY_VP": "公司",
    "BUSINESS_VP": "业务线VP",
    "HR_VP": "HRVP",
    "HRD": "HRD",
    "COMPANY_COL_PREX": "企业大学校长",
    "T_DIRECTOR": "培训总监",
    "HR_MANAGER": "人力资源经理",
    "T_MANAGER": "培训经理"
};
var queyouqishixqObj = {
    "BID": "竞标",
    "NFG": "竞争性谈判",
    "PRE_BID": "筹备竞标中",
    "MONOPOLY": "就和兜行一家谈判",
    "INFO_COLLECTION": "供应商信息收集中",
    "PROPOSAL": "给决策者制作方案中"
};
var yiwaishiqxqObj = {
    "BUS_NO_FEEL": "业务部门无感",
    "NO_PLAT_DOUBT_RESULT": "企业没上过平台担心效果",
    "HAVE_PLAT_NO_GOOD_BOUBT_RESULT": "企业上过平台，效果不好，再上担心效果",
    "HR_NEED_BUS_MO": "HR认为有需求，业务部门没有"
};
var shiyongcpxgObj = {
    "PLEASED": "决策者已经试用且满意",
    "POL_TRIED_NORESULT": "决策者已经试用，态度不知道",
    "OPN_TRIED": "运营负责人试用",
    "ACC_TRIED": "助理试用了",
    "EMP_TRIED": "一部分员工参与了试用"
};
var shiyongcpwmyObj = {
    "BUS_NO_FEEL": "业务部门无感",
    "NO_PLAT_DOUBT_RESULT": "企业没上过平台担心效果不好",
    "HAVE_PLAT_NO_GOOD_BOUBT_RESULT": "企业上过平台，效果不好，再上担心效果",
    "HR_NEED_BUS_NO": "HR认为有需求，业务部门没有"
};

var xiayibujcrxdObj = {
    "FINDING_POL": "判断谁是决策人中",
    "FINDING_POL_PROBLEM": "判断谁是决策人中遇到了困难",
    "DATEING_POL": "约见决策人中",
    "DATEING_POL_PROBLEM": "约见决策人中遇到了困难",
    "DOING_POL": "搞定决策人中",
    "DOING_POL_PROBELM": "搞定决策人过程中遇到了困难"
};
var xuqiuxdObj = {
    "COMPANY_HAVE_REQ": "企业有需求，待做实",
    "BUSINESS_HAVE_REQ": "业务部门有需求，待做实",
    "HR_HAVE_REQ": "HR部门有需求，待做实"
};


//决策人
function select_menu_jueceren() {

    for (x in juecerenObj) {
        $("#select_menu_jueceren").append('<li onclick="selectchange_jcr(this)" data-number="' + x + '"><a><label class="checkbox">' + juecerenObj[x] + '</label></a></li>')
    }
}
function selectchange_jcr(el) {
    $("#seleCheckbox_0 span").text($(el).text());
}
//决策人职位
function select_menu_jcrzw() {

    for (x in juecerenzhiweiObj) {
        $("#select_menu_jcrzw").append('<li onclick="selectchange_jcrzw(this)" data-number="' + x + '"><a><label class="checkbox">' + juecerenzhiweiObj[x] + '</label></a></li>')
    }
}
function selectchange_jcrzw(el) {
    $("#seleCheckbox_1 span").text($(el).text());
}

//是否搞定决策人
function select_menu_gdjcr() {
    for (x in juecerenObj) {
        $("#select_menu_gdjcr").append('<li onclick="selectchange_gdjcr(this)" data-number="' + x + '"><a><label class="checkbox">' + juecerenObj[x] + '</label></a></li>')
    }
}
function selectchange_gdjcr(el) {
    $("#seleCheckbox_2 span").text($(el).text());
}
//搞定决策人的职位
function select_menu_gdjcrzw() {
    for (x in juecerenzhiweiObj) {
        $("#select_menu_gdjcrzw").append('<li onclick="selectchange_gdjcrzw(this)" data-number="' + x + '"><a><label class="checkbox">' + juecerenzhiweiObj[x] + '</label></a></li>')
    }
}
function selectchange_gdjcrzw(el) {
    $("#seleCheckbox_3 span").text($(el).text());
}

//确有其事详情
function select_menu_qs() {
    for (x in queyouqishixqObj) {
        $("#select_menu_qs").append('<li onclick="selectchange_qs(this)" data-number="' + x + '"><a><label class="checkbox">' + queyouqishixqObj[x] + '</label></a></li>')
    }
}
function selectchange_qs(el) {
    $("#seleCheckbox_4 span").text($(el).text());
}

//意外事情详情
function select_menu_qyqsbz() {
    for (x in yiwaishiqxqObj) {
        $("#select_menu_qyqsbz").append('<li onclick="selectchange_qyqsbz(this)" data-number="' + x + '"><a><label class="checkbox">' + yiwaishiqxqObj[x] + '</label></a></li>')
    }
}
function selectchange_qyqsbz(el) {
    $("#seleCheckbox_5 span").text($(el).text());
}

//5人以上使用产品效果
function select_menu_sycp() {
    for (x in shiyongcpxgObj) {
        $("#select_menu_sycp").append('<li onclick="selectchange_sycp(this)" data-number="' + x + '"><a><label class="checkbox">' + shiyongcpxgObj[x] + '</label></a></li>')
    }
}
function selectchange_sycp(el) {
    $("#seleCheckbox_6 span").text($(el).text());
}
//为满足5人以上使用产品效果
function select_menu_sycpbz() {
    for (x in shiyongcpwmyObj) {
        $("#select_menu_sycpbz").append('<li onclick="selectchange_sycpbz(this)" data-number="' + x + '"><a><label class="checkbox">' + shiyongcpwmyObj[x] + '</label></a></li>')
    }
}
function selectchange_sycpbz(el) {
    $("#seleCheckbox_7 span").text($(el).text());
}

//下一步决策人动作
function select_menu_jcrdz() {
    for (x in xiayibujcrxdObj) {
        $("#select_menu_jcrdz").append('<li onclick="selectchange_jcrdz(this)" data-number="' + x + '"><a><label class="checkbox">' + xiayibujcrxdObj[x] + '</label></a></li>')
    }
}
function selectchange_jcrdz(el) {
    $("#seleCheckbox_8 span").text($(el).text());
}

//下一步需求动作
function select_menu_xqdz() {
    for (x in xuqiuxdObj) {
        $("#select_menu_xqdz").append('<li onclick="selectchange_xqdz(this)" data-number="' + x + '"><a><label class="checkbox">' + xuqiuxdObj[x] + '</label></a></li>')
    }
}
function selectchange_xqdz(el) {
    $("#seleCheckbox_9 span").text($(el).text());
}

function from_btns() {//提交表单
    $("#submit_btn").click(function () {

        newMainData = new Object();

        newMainData.cusName = $("#cusName").val();


        newMainData.seenPolicymaker = $("#seenPolicymaker").text();
        newMainData.policymakerPosition = $("#policymakerPosition").text();
        newMainData.donePolicymaker = $("#donePolicymaker").text();
        newMainData.donePolicymakerPosition = $("#donePolicymakerPosition").text();
        newMainData.isReal = $("#isReal").text();
        newMainData.isRealComment = $("#isRealComment").text();
        newMainData.fiveUserUp = $("#fiveUserUp").text();
        newMainData.fiveUserUpComment = $("#fiveUserUpComment").text();
        newMainData.contactName = $("#contactName").val();
        newMainData.contactPhone = $("#contactPhone").val();
        newMainData.contactEmail = $("#contactEmail").val();
        newMainData.cusEmpNum = $("#cusEmpNum").val();
        newMainData.cusCity = $("#cusCity").val();
        newMainData.launchTime = $("#launchTime").val();
        newMainData.closeTime = $("#closeTime").val();
        newMainData.nextPolicymakerAction = $("#nextPolicymakerAction").text();
        newMainData.nextReqAction = $("#nextReqAction").text();
        newMainData.importantReq = $("#importantReq").val();


        $.ajax({
            type: 'GET',
            url: 'api/saleMainData/insert',
            cache: false,
            dataType: 'json',
            data:newMainData,
            success: function (datas) {
                console.log(datas);
            }
        });
    });
}
function reset_btns() {//重置表单
    $("#reset_btn").click(function () {
        $(".btn-group button span").text("请选择");
    });
}