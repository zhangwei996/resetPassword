<template>
  <div>
    <!-- 答题进度 -->
    <div v-if="progress" id="progress-box" :class="{'progress':true,'fixed' : isFixed}">
      <div class="progress-bar">
        <div class="progress-done" :style="{width:progWidth*100+'%'}"></div>
      </div>
    </div>
    <!-- 题目列表 -->
    <div class="ques-item" v-for="(item,index) in quesList">
      <div class="ques-name" :id="'ques_'+timestamp+'_'+index">
        <span class="ques-order-span">{{index+1}}.</span>
        <!-- 填空题 -->
        <ques-fill v-if="item.type==4" :quesName="item.name" v-model="item.answer" :required="item.config.required"
          @answer="quesChange(item,index)"></ques-fill>
        <span v-else>{{item.name}}</span>
        <span v-if="item.config.required&&item.type!=4" class="ques-required">*</span>
        <span v-if="errorTagShow(item,index)" class="ques-error">这道题未回答</span>
      </div>
      <div>{{item.description}}</div>
      <ques-option :quesType="item.type" :quesConfig="item.config" v-model="item.answer"
        @answer="quesChange(item,index)"></ques-option>
    </div>
    <div class="mask-bg" v-if="disabled"></div>
  </div>
</template>

<script>
  import quesOption from "@/components/quesOption.vue";
  import quesFill from "@/components/quesFillBlank.vue";
  export default {
    components: {
      quesOption,
      quesFill
    },
    props: {
      quesList: Array,
      //进度条是否显示
      progress: {
        type: Boolean,
        default: false,
      },
      disabled: {
        type: Boolean,
        default: false,
      },
    },
    model: {
      prop: "quesList",
      event: "awser"
    },
    data() {
      return {
        timestamp: new Date().getTime() + '_' + Math.floor(Math.random() * 1000),
        currentIndex: -1,
        isFixed: false,
        progWidth: 0,
      };
    },
    mounted() {
      setTimeout(() => {
        this.linkAnchor();
      }, 0);
      window.offsetTop = this.getElementTop(document.querySelector("#progress-box"));;
      window.addEventListener("scroll", this.handleScroll); // 监听滚动事件
    },
    methods: {
      quesCheckRequired() {
        const _this = this;
        let arr = _this.quesList,
          state = true;
        for (let i = 0; i < arr.length; i++) {
          const element = arr[i];
          if (!element.config.required) continue;
          let checked = this.isCheck(element);
          if (!checked) {
            this.currentIndex = i;
            this.linkAnchor();
            state = false;
            break;
          }
        }
        // console.log(this.currentIndex);
        return state;
        // return this.currentIndex == -1;
      },
      linkAnchor() {
        let index = this.currentIndex;
        if (index == -1) return;
        let id = "ques_" + this.timestamp + "_" + index;
        let ques = document.getElementById(id);
        let tipsTop = document.body.clientWidth > 768 ? 40 : 10;
        document.documentElement.scrollTop = this.getElementTop(ques) - tipsTop;
        this.currentIndex = index;
      },
      isCheck(_ques) {
        let checked = true,
          firstAnswer = _ques.answer[0];
        //矩阵
        if ([7, 10].includes(_ques.type)) {
          let arrlen = _ques.config.questions.length;
          if (_ques.answer.length != arrlen) checked = false;
        } else if (_ques.type == 8) {
          _ques.answer.forEach(element => {
            if (element.length == 0) checked = false;
          });
        }
        if ([12].includes(_ques.type)) {
          let arr = [];
          _ques.answer.forEach(element => {
            element.forEach(celement => {
              if (celement.length > 0 && celement) arr.push(celement);
            });
          });
          if (arr.length == 0) checked = false;
        } else if (_ques.type == 3 && firstAnswer == -1) checked = false;
        else if (_ques.type == 4 && !_ques.answer[0]) checked = false;
        else if (_ques.type == 13 && !_ques.answer[0]) checked = false;
        else if (_ques.answer.length == 0) checked = false;
        else {
          //其他想到再补充
        }
        return checked;
      },
      errorTagShow(_ques, index) {
        const _this = this;
        if (_this.currentIndex == index && _ques.config.required) {
          return !_this.isCheck(_ques);
        } else return false;
      },
      handleScroll() {
        let scrollTop =
          window.pageYOffset ||
          document.documentElement.scrollTop ||
          document.body.scrollTop; // 滚动条偏移量
        // console.log(offsetTop,scrollTop);
        this.isFixed = scrollTop > window.offsetTop ? true : false; // 如果滚动到顶部了，this.isFixed就为true
      },
      getElementTop(el) {
        var actualTop = el.offsetTop;
        var current = el.offsetParent;
        while (current !== null) {
          actualTop += current.offsetTop;
          current = current.offsetParent;
        }
        return actualTop;
      },
      // quesChange(ques,index){
      //     const _this=this;
      //     let allLen=_this.quesList.length,requLen=0,awserLen=0;
      //     _this.quesList.forEach(x=>{
      //         if(x.config.required) requLen++;
      //         if(_this.isCheck(x))awserLen++;
      //     })
      //     // console.log(allLen,requLen,awserLen);
      //     if(ques.config.required) _this.progWidth=(awserLen)/requLen;
      // },
      quesChange(ques, index) {
        const _this = this;
        let allLen = _this.quesList.length,
          requLen = 0,
          awserLen = 0;
        _this.quesList.forEach(x => {
          if (x.config.required) {
            requLen++;
            if (_this.isCheck(x)) awserLen++;
          }
        })
        // console.log(allLen,requLen,awserLen);
        if (ques.config.required) _this.progWidth = (awserLen) / requLen;
      },
    }
  };

</script>

<style lang="scss">
  .mask-bg {
    position: absolute;
    width: 100%;
    height: 100%;
    z-index: 100;
    top: 0;
    left: 0;
  }

</style>
