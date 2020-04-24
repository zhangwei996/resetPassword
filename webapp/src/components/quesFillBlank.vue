<template>
    <!-- 填空题解析 -->
    <div class="clearfix fill-box">
        <div class="fl" v-for="(item,index) in fillList">
            <div  v-if="typeof item === 'number' ">
                <input
                    type="text"
                    :tabindex="index+1"
                    class="fill-input"
                    :style="{'width':inputWidth(index)+'px',}"
                    v-model="quesFillList[getNumIndex(index)]"
                    maxlength="30"
                />
                <!-- <span :id="timestamp+'js_width_fill_'+getNumIndex(index)">{{quesFillList[getNumIndex(index)]}}</span> -->
            </div>
            <span v-else class="fl">{{item}}</span>
        </div>
        <span v-if="required" class="ques-required">*</span>
    </div>
</template>

<script>
import editInput from "@/components/contentEdiInput.vue";
export default {
    props: {
        quesName: String,
        quesAnswer:Array,
        required:Boolean,
    },
    components: { editInput },
    model: {
        prop:"quesAnswer",
        event: "answer"
    },
    data() {
        return {
            quesFillList: this.quesAnswer,
            timestamp: new Date().getTime()+'_'+  Math.floor(Math.random()*1000),
        };
    },
    computed: {
        fillList() {
            var iarr = [],
                ctn = 0,
                array = this.quesName;
            for (let i = 0; i < array.length; i++) {
                const element = array[i];
                if (element == "_" && array[i + 1] == "_") {
                    ctn++;
                } else if (array[i - 1] == "_" && ctn > 0) {
                    iarr.push(ctn + 1);
                    ctn = 0;
                } else {
                    iarr.push(element + "");
                }
            }
            return iarr;
        }
    },
    watch: {
        quesFillList(newVal, oldVal) {
            this.$emit("answer", newVal);
        }
    },
    created() {
        this.$emit("answer",[]);
    },
    methods: {
        getNumIndex(index) {
            let numIndex = 0;
            for (let i = 0; i < this.fillList.length; i++) {
                const element = this.fillList[i];
                if (index == i) break;
                if (typeof element === "number") numIndex++;
            }
            return numIndex;
        },
        inputWidth(index) {
            let nIndex = this.getNumIndex(index);
            let defWidth=this.fillList[index]*12,txtWidth=0;
            let copyDom=document.getElementById(this.timestamp+'js_width_fill_'+nIndex);
            if(copyDom){
              txtWidth= copyDom.clientWidth || copyDom.offsetWidth
            }
            // return defWidth>txtWidth?defWidth:txtWidth;
            return defWidth;
        }
    }
};
</script>

<style lang="scss" scoped>
.fill-box{
    display: inline-block;
    position: relative;
    top: 4px;
}
.fill-input {
    height: 24px;
    outline: none;
    border-bottom: 1px solid #333;
    margin: 0 6px;
    color: rgba(40, 99, 243, 1);
    float: left;
    width: auto;
    max-width: 100%;
}
</style>