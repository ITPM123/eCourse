<template>
  <div>
    <a-card title="公告信息" class="card">
      <div style="min-width:20rem;min-height:3rem">标题：{{announcement.title}}</div>
      <div style="min-width:20rem;min-height:3rem">内容：{{announcement.content}}</div>
      <!-- 附件:
      <a :href="'/api/download/attachment/'+announcement.attachment1">{{announcement.fileName1}}</a>
      <br>
      <a :href="'/api/download/attachment/'+announcement.attachment2">{{announcement.fileName2}}</a>
      <br>
      <a :href="'/api/download/attachment/'+announcement.attachment3">{{announcement.fileName3}}</a>
      <br> -->
    </a-card>
  </div>
</template>

<style>
.card {
  margin: 50px 20px;
}
</style>

<script>
export default {
  name: "announcementContent",
  mounted() {
    this.init();
  },
  data: function() {
    return {
      announcement: {
        title: "",
        content: "",
        fileName1: "",
        fileName2: "",
        fileName3: ""
      }
    };
  },
  methods: {
    init() {
      this.announcement = this.$store.state.schoolAnnouncement;
      console.log(this.$store.state.schoolAnnouncement);
    }
  }
};
</script>
