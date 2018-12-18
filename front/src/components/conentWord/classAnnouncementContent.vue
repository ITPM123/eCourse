<template>
  <div>
    <a-card title="公告信息" class="card">
      <div style="min-width:20rem;min-height:3rem">标题：{{announcement.title}}</div>
      <div style="min-width:20rem;min-height:3rem">内容：{{announcement.content}}</div>
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
        content: ""
      }
    };
  },
  methods: {
    init() {
      this.announcement = this.$store.state.courseAnnouncement;
      console.log(this.$store.state.courseAnnouncement);
    }
  }
};
</script>

