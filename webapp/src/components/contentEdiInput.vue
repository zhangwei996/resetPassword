<template>
    <!-- 可编辑输入框 -->
    <p
        class="edit-div"
        v-html="innerText"
        :contenteditable="canEdit"
        @focus="isLocked = true"
        @blur="isLocked = false"
        @input="changeText"
    ></p>
</template>
<script type="text/ecmascript-6">

export default {
    name: "editDiv",
    props: {
        value: {
            type: String,
            default: ""
        },
        canEdit: {
            type: Boolean,
            default: true
        }
    },
    data() {
        return {
            innerText: this.value,
            isLocked: false
        };
    },
    watch: {
        value() {
            if (!this.isLocked || !this.innerText) {
               this.innerText = this.value;
            }
        },
    },
    mounted(){
    },
    methods: {
        changeText() {
            // let str=this.$el.innerHTML,tempStr='';
            // for (let i = 0; i < str.length; i++) {
            //     const element = str[i];
            //     if(encodeURI(element)!='%0A')tempStr+=element;
            // }
            // tempStr=123;
            // this.innerText=1;
            // this.$emit("input", tempStr);
            // this.$emit("input", this.$el.innerHTML);
            var tempStr=this.$el.innerHTML;
            tempStr = tempStr.replace(/(\n)/g, "");  
            tempStr = tempStr.replace(/(\t)/g, "");  
            tempStr = tempStr.replace(/(\r)/g, "");  
            tempStr = tempStr.replace(/<\/?[^>]*>/g, "");  
            tempStr = tempStr.replace(/\s*/g, "");
            this.$emit("input", tempStr);
        },
    },
};
</script>
<style lang="scss" rel="stylesheet/scss">
.edit-div {
    width: 100%;
    height: 100%;
    word-break: break-all;
    user-select: text;
    white-space: pre-wrap;
    text-align: left;
    &[contenteditable="true"] {
        user-modify: read-write-plaintext-only;
        &:empty:before {
            content: attr(placeholder);
            display: block;
            color: #ccc;
        }
    }
}
</style>