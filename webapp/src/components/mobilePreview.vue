<template>
  <div class="preview-wrap">
      <ul class="preview-ul clearfix">
          <li class="active"><i class="iconfont icon-shouji"></i>手机预览</li>
          <li @click="pcPreview"><i class="iconfont icon-diannao"></i>电脑预览</li>
      </ul>
      <div class="preview-box">
          <div  id="ques-box">
               <div class="ques-page" id="ques-page" >
                <div class="ques-wrap">
                    <h3 class="ques-title">{{quesJson.title}}</h3>
                    <h4 class="ques-explain">{{quesJson.description}}</h4>
                    <div class="ques-box">
                        <ques-item :progress="true" :disabled="true" v-model="quesJson.questionnaireQestionList" ref="quesItem"></ques-item>
                    </div>
                </div>
            </div>
          </div>
      </div>
  </div>
</template>

<script>
import quesItem from "@/components/quesItem.vue";
export default {
    props: {
        quesJson: Object,
    },
    components: { quesItem },
    methods:{
        pcPreview(){
            sessionStorage.setItem("currentQues", JSON.stringify(this.quesJson));
            window.open('#/preview');
        }
    },
}
</script>
<style lang="scss">
 @import '../assets/survey.mobile.scss';

.preview-box .progress{
    position: absolute !important;;
}
</style>
<style lang="scss" scoped>
.ques-wrap .ques-box{
    position: relative;
}
.preview-ul{
    margin-bottom:8px;
    li{
        float: left;
        width: 50%;
        padding: 0px 15px;
        margin-bottom: 10px;
        font-size: 14px;
        cursor: pointer;
        i{margin-right: 5px;}
        &:first-child{text-align: right;}
    }

    li.active{
        color: #018fff;
        cursor: default;
    }
}
.preview-box{
    zoom: .7;
    -moz-transform:scale(0.7);
    -moz-transform-origin:top center;
    background: url('../../static/img/bg_preview_mobile.png') no-repeat center / cover;
    width:355px*1.16;
    height: 755px*1.16;
    margin: 0 auto;
    position: relative;
    &>#ques-box{
       position: absolute;
        width: 370px;
        height: 660px;
        // border: 1px solid red;
        left: 24px;
        top: 104px;
        overflow-y:scroll;
    }

}

</style>
