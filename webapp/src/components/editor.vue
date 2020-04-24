<template>
    <div>
        <quill-editor v-model="content"
            ref="myTextEditor"
            :options="editorOption"
            @change="onChange"
        >
            <div id="toolbar" slot="toolbar">
                <span class="ql-formats"><button type="button" class="ql-bold"></button></span>
                <span class="ql-formats"><button type="button" class="ql-italic"></button></span>
                <span class="ql-formats"><button type="button" class="ql-underline"></button></span>
                <span class="ql-formats"><button type="button" class="ql-strike"></button></span>
                <!-- 有序和无序 -->
                <span class="ql-formats"><button type="button" class="ql-list" value="ordered"></button></span>
                <span class="ql-formats"><button type="button" class="ql-list" value="bullet"></button></span>
                <!-- 缩进 -->
                <span class="ql-formats"><button type="button" class="ql-indent" value="-1"></button></span>
                <span class="ql-formats"><button type="button" class="ql-indent" value="+1"></button></span>
                <span class="ql-formats"> <button type="button" class="ql-direction" value="rtl"></button></span>
                <!-- 自定义下拉 -->
                <span class="ql-formats">
                    <select class="ql-size" id="ql-size">
                        <option value="small">小号</option>
                        <option value="">正常</option>
                        <option value="large">大号</option>
                        <option value="huge">特大</option>
                    </select>
                    <!-- <i-select size="small" @on-change="mychange">
                        <i-option value="small">小号</i-option>
                        <i-option value="large">正常</i-option>
                        <i-option value="huge">特大</i-option>
                    </i-select> -->
                </span>
                <span class="ql-formats">
                    <select class="ql-header">
                        <option value="1">标题1</option>
                        <option value="2">标题2</option>
                        <option value="3">标题3</option>
                        <option value="4">标题4</option>
                        <option value="5">标题5</option>
                        <option value="6">标题6</option>
                        <option selected="selected">正文</option>
                    </select>
                </span>
                <span class="ql-formats">
                    <select class="ql-color" v-model="fontColor">
                        <option v-for="item in colorArry" :key="item" :value="'#'+item"></option>
                    </select>
                </span>
                <span class="ql-formats"> 
                    <select class="ql-background" v-model="bgColor">
                        <option v-for="item in colorArry" :key="item" :value="'#'+item"></option>
                    </select>
                </span>
                <span class="ql-formats" v-if="false">
                    <select class="ql-font">
                            <option selected="selected"></option>
                            <option value="serif"></option>
                            <option value="monospace"></option>
                        </select>
                </span>
                <span class="ql-formats">
                    <select class="ql-align">
                        <option selected="selected"></option>
                        <option value="center"></option>
                        <option value="right"></option>
                        <option value="justify"></option>
                    </select>
                </span>
                <span class="ql-formats" v-if="false">
                    <button type="button" @click="isLink=true" class="ql-link"></button>
                </span>
                 <span class="ql-formats"  v-if="false">
                     <button type="button" class="my-formats-image">
                         <Upload 
                            :action="formItem.uploadUrl"
                            :on-success="handleSuccess">
                             <Icon type="image" size="16"></Icon> 
                        </Upload>
                     </button>
                </span>
            </div>
        </quill-editor>
        <!-- 添加链接 -->
		<Modal v-model="isLink" title="添加链接"  width="450px">
            <i-form  :label-width="85" class="myform" ref="formItem.link" :model="formItem.link">
                <Form-Item label="链接地址：" prop="url">
                    <i-input v-model="formItem.link.url"  placeholder="请输入链接地址"></i-input>
                </Form-Item>
                <Form-Item label="链接名称：" prop="tit">
                    <i-input v-model="formItem.link.tit" placeholder="请输入链接名称"></i-input>
                </Form-Item>
            </i-form>
            <div slot="footer">
                <i-Button type="primary" @click="addLinkSave()">提交</i-Button>
                <i-Button type="ghost" @click="myCancel('isLink','formItem.link')"  style="margin-left: 8px">取消</i-Button>
            </div>
           
        </Modal>	
    </div>
</template>
<script>
    import {quillEditor} from 'vue-quill-editor'

    export default {
        components: {quillEditor},
        props: {
            // 提示内容
            placeholder:{
                type: String
            },
            /*编辑器内容*/
            value: {
                type: String
            },
            /*上传图片的地址*/
            uploadUrl: {
                type: String
            },
        },
        data() {
            return {
                content:this.value,
                size:'',
                fontColor:"000000",
                bgColor:"ffffff",
                colorArry:['000000','e60000','ff9900','ffff00','008a00','0066cc','9933ff','ffffff','facccc','ffebcc','ffffcc','cce8cc','cce0f5','ebd6ff','bbbbbb','f06666','ffc266','ffff66','66b966','66a3e0','c285ff','888888','a10000','b26b00','b2b200','006100','0047b2','6b24b2','444444','5c0000','663d00','666600','003700','002966','3d1466'],
                editorOption: {
                    modules: {
                        toolbar: '#toolbar',
                    },
                    placeholder:this.placeholder || '请输入内容……',
                },
                showCrop:false,
                isLink:false,
                isImage:false,
                formItem:{
                    link:{
                        url:'',
                        tit:'',
                    },
                    uploadUrl:this.uploadUrl || '',
                }
            }
        },
        methods: {
            onChange(){
                this.$emit('input', this.content)
            },
            myCancel(name, resetName) {
                var vm = this;
                vm[name] = false;
                if (resetName) {
                    console.log(vm.$refs[resetName])
                    this.$refs[resetName].resetFields();
                }
            },
            addLinkSave(){
              var _that=this;
              _that.isLink=false;
              _that.editor.focus();
              _that.editor.insertText(_that.editor.getSelection().index, ' ', '', true);
              _that.editor.insertText(_that.editor.getSelection().index, _that.formItem.link.tit+' ', 'link','http://'+ _that.formItem.link.url);
            },
            handleSuccess(a,b,c){
              this.editor.focus();
              console.log(a,b,c);
              var url="https://o5wwk8baw.qnssl.com/a42bdcc1178e62b4694c830f028db5c0/large";
              this.editor.insertEmbed(this.editor.getSelection().index, 'image', url);
                console.log(233);
            },
            // onFocus(){
            //     $(".ql-editor img").removeClass("active");
            // },
        },
        computed: {
            editor() {
                return this.$refs.myTextEditor.quill;
            }
        },
        
        mounted(){
            var _that=this;
            // $(".ql-editor img").on('click',function(){
            //     _that.editor.blur();
            //     $(this).addClass("active");
            // })
        },
        watch: {
            'value'(newVal, oldVal) {
                if (this.editor) {
                    if (newVal !== this.content) {
                        this.content = newVal
                    }
                }
            },
        }
    }
</script>
<style lang="scss">
.ql-tooltip{
    display: none !important;
}

.my-formats-image{
    outline: none;
    ul.ivu-upload-list{
        display: none;
    }
}
.ql-container .ql-editor {
    min-height:5em;
    padding-bottom: 1em;
    max-height: 25em;
  }
  .ql-editor{
    position: relative;
    img{
      position: relative;
    }
    img::after {
         content:'123123';
          display: block;
          width: 12px;
          height: 12px;
          position: absolute;
          top:0;right: 0;
          background: red;
    }
    
  }  
</style>