<template>
  <div>
    <li v-for="drug in list">
      {{ drug.drugcode}}
      {{ drug.drugname }}
      {{ drug.drugnamepinyin }}
      {{ drug.spec }}
      {{ drug.agentname }}
      {{ drug.manufacturername }}
      {{ drug.dosepkgname }}
    </li>
  </div>

</template>

<script>
  import axios from 'axios'
  export default {
    name: 'app-main',
    data(){
      return {
        list:[]
      }
    },
    created:function(){
      axios.get('/producer/getDrugList')
        .then(res => {
          console.log(res)
          if (res.status == 200) {
            this.list = res.data;
          }
        });

    },
  }
</script>

<style scoped>

</style>
