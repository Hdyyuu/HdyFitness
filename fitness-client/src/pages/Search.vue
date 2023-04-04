<template>
  <div class="search">
    <h1>搜索结果</h1>
    <content-list :contentList="searchVideo" path="video-list-album"></content-list>
  </div>
</template>

<script>
import mixin from '../mixins'
import { mapGetters } from 'vuex'
import ContentList from "../components/ContentList";

export default {
  name: 'search',
  mixins: [mixin],
  components: {
    ContentList
  },
  data () {
    return {
    }
  },
  computed: {
    ...mapGetters([
      'searchword',
      'searchVideo'
    ])
  },
  watch: {
    searchword: function () {
      this.getSearch()
    }
  },

}
</script>

<style lang="scss" scoped>
@import '../assets/css/search.scss';
</style>
