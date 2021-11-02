<template>
  <div ref='editor'></div>
  <button @click='syncHTML'>同步内容</button>
  <button @click="saveToDB">保存至数据库</button>
  <div :innerHTML='content.html'></div>
</template>

<script>
import { onMounted, onBeforeUnmount, ref, reactive } from 'vue';
import WangEditor from 'wangeditor';
import axios from 'axios'
import {Base64} from "js-base64";
export default {
  name: 'app',
  setup() {
    const editor = ref();
    const content = reactive({
      html: '',
      text: '',
    });
    let instance;
    onMounted(() => {
      instance = new WangEditor(editor.value);
      const data = new FormData()
      data.append("id", "1455445303942975488")
      axios({
        url: "/api/wangEditor/issue/findById",
        method: 'post',
        data: data
      }).then(res => {
        content.html = Base64.decode(res.data.data.description)
        instance.txt.html(content.html)
      }).catch(res => {
        console.log(res)
      })
      Object.assign(instance.config, {
        onchange() {
          console.log(instance.txt.html())
          console.log(instance.txt.getJSON())
        },
      });
      instance.config.customUploadImg = function (resultFiles, insertImgFn) {
        const data = new FormData()
        for (let i = 0; i< resultFiles.length; i++) {
          data.append("files", resultFiles[i])
        }
        data.append("issueId", 1);
        axios({
          url: "/api/wangEditor/attachment/upload",
          method: 'post',
          headers: {
            'Content-Type': 'multipart/form-data;charset=UTF-8'
          },
          data: data
        }).then(res => {
          res.data.data.forEach(attachment => {
            insertImgFn(attachment.url)
          })
        }).catch(res => {
          console.log(res)
        })
      }
      instance.create();
    });
    onBeforeUnmount(() => {
      instance.destroy();
      instance = null;
    });
    const syncHTML = () => {
      content.html = instance.txt.html();
    };
    const saveToDB = () => {
      const data = new FormData()
      data.append("description", instance.txt.html())
      console.log(data)
      axios({
        url: '/api/wangEditor/issue/save',
        method: 'post',
        data
      }).then(res => {
        if (res.data.code == 200) {
          console.log("保存成功")
        }
      }).catch(res => {
        console.log(res)
      })
    }
    return {
      syncHTML,
      editor,
      content,
      saveToDB
    };
  },
};
</script>