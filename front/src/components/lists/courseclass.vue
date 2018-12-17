<template>
  <a-list :grid="{ gutter: 16, xs: 1, sm: 2, md: 4, lg: 4, xl: 6, xxl: 4 }" :dataSource="data">
    <a-list-item slot="renderItem" slot-scope="item, index" @click="clickhandle">
      <template>
        <a-card hoverable style="margin:20%">
          <img alt="example" src="@/assets/logo.svg" slot="cover">
          <center>
            <a-card-meta :title="item.title"/>
          </center>
        </a-card>
      </template>
    </a-list-item>
  </a-list>
</template>


<script>
import axios from "axios";

const data = [
  {
    title: "CLASS 1",
    src: ""
  },
  {
    title: "CLASS 2"
  },
  {
    title: "CLASS 3"
  },
  {
    title: "CLASS 4"
  }
];

export default {
  data() {
    return {
      data
    };
  },
  methods: {
    clickhandle() {
      this.$store.commit("changeContent", "classStudent");
    }
  }
};
</script>
<style>
</style>