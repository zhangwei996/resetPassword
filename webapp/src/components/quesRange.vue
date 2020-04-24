<template>
    <!-- 量表题 -->
    <div class="my-range-wrap">
        <div class="range-head clearfix">
            <span class="fl mr10 my-range-tips">非常不{{rangeType}} </span>
            <span class="fr my-range-tips">非常{{rangeType}} </span>
        </div>
        <div class="range-body clearfix">
            <span class="fl mr10">1</span>
            <div v-if="rangeNum<11" class="clearfix fl ">
                <i class="iconfont icon-liangbiaoti fl mr10" 
                    v-for="(item,index) in rangeNum"
                    @click="rangeValue=index+1"
                    :class="rangeValue>index?'active':''"
                ></i>
            </div>
            <div class="range-input" v-else >
                <input v-model="rangeValue"  class="my-range fl" type="range" min="1"  :max="rangeNum" :name="timestamp" />
                <div class="range-tips-box">
                    <span :style="{left:rangeLeft}"> {{rangeValue}}</span>
                </div>
            </div>
            <span class="fl ml10">{{rangeNum}}</span>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        rangeType: String,
        rangeNum:{
            type:Number,
            default:5,
        },
        quesAnswer:Number,
    },
    model: {
        prop:'quesAnswer',
        event: "answer"
    },
    data() {
        return {
            timestamp: new Date().getTime()+'_'+  Math.floor(Math.random()*1000),
            rangeValue:this.quesAnswer || 0,
        };
    },
     computed:{
        rangeLeft(){
            let num=(this.rangeValue-1)/(this.rangeNum-1)*100;
            return num+'%';
        }
    },
    watch:{
        rangeNum(currVal){
            console.log(currVal);
            this.rangeNum=parseInt(currVal);
            let num=parseInt(currVal);
            if(num<11)this.rangeValue=0;
            else  this.rangeValue= Math.floor(num/2);
        },
        rangeValue(currVal){
            this.$emit('answer',parseInt(currVal) || 0);
        },
    },
    created(){
        if(this.rangeValue==0){
            let num=this.rangeNum;
            if(num<11)this.rangeValue=0;
            else  this.rangeValue= Math.floor(num/2);
        }
    }
}
</script>
<style lang="scss" scoped>
$color:#409eff;
.my-range{
    width: 260px;
    // height: 32px;
    -webkit-appearance: none; /*去除默认样式*/
    margin-top: 14px;
    background-color: #f2f2f2;
    border-radius: 15px;
    -webkit-appearance: none;
    height:4px;
    padding: 0;
    border: none;
    outline: 0;
}
.my-range::-webkit-slider-thumb{
    -webkit-appearance: none;/*去除默认样式*/
    cursor: default;
    top: 0;
    height: 20px;
    width: 20px;
    transform: translateY(0px);
    background: #fff;
    border-radius: 15px;
    border: 5px solid $color;
}
.my-range::-moz-range-thumb{
    -webkit-appearance: none;/*去除默认样式*/
    cursor: default;
    top: 0;
    height: 10px;
    width:10px;
    transform: translateY(0px);
    /*background: none repeat scroll 0 0 #5891f5;*/
    background: #fff;
    border-radius: 15px;
    border: 5px solid $color;
}
@media screen and (-ms-high-contrast: active), (-ms-high-contrast: none) {
 .my-range{
     margin-top:4px;
     height: 22px;
 }
}
input[type=range]::-ms-fill-lower {//::-ms-fill-lower这是ie兼容range的属性写法下面那个同理
    /*进度条已填充的部分*/
    height: 22px !important;
    border-radius: 10px;
    /*background: black;*/
    background: linear-gradient(to right, #059CFA, white 100%, white);
}

input[type=range]::-ms-fill-upper {
    /*进度条未填充的部分*/
    height: 22px !important;
    border-radius: 10px;
    background: #f2f2f2;
}

.range-body{
    max-width:330px;
    display: flex;
    justify-content:center;
    i{
        -webkit-tap-highlight-color:rgba(0,0,0,0);
    }
}

.range-head{
     max-width:330px;
}

.range-input{
    position: relative;
    .range-tips-box{
        width:240px;
        height: 1px;
        left:10px;
        position: relative;
        top:0px;
        &>span{
            display: none;
            position: absolute;
            transition: opacity 500ms;
            top:-38px;
            width:30px;
            height: 30px;
            background: $color;
            color: #fff;
            border-radius: 3px;
            text-align: center;
            line-height: 30px;
            transform: translateX(-15px);
            &::after{
                position: relative;
                left:9px;
                display: block;
                width:0;
                height: 0;
                content: '';
                border-width: 6px;
                border-style: solid;
                border-color:$color transparent transparent transparent;

            }
        }
    }
    
     input:focus+.range-tips-box span,input:active+.range-tips-box span,input:hover+ .range-tips-box span{
        display: block;
        animation: shows 500ms;
    }

    @keyframes shows
    {
        from { opacity: 0; }
        to {opacity: 1;}
    }

}

.my-range-wrap{
    margin:10px 0;
    position: relative;
    left: 50%;
    transform: translateX(-50%);
    width: 330px;
    line-height: 30px;
    .my-range-tips{
        font-size: 12px;
        background: #f2f2f2;
        line-height: 24px;
        height: 24px;
        margin-top:4px;
        padding: 0 5px;
        border-radius: 4px;
    }
    i{
        cursor: pointer;
        font-size: 18px;
        color:$color;
    }
    i.active{
        color: $color;
        &::before {
            content: "\e692";
        }
    }
}

@media screen and (max-width: 768px) {
  .range-body i{font-size: 20px;}
}
</style>