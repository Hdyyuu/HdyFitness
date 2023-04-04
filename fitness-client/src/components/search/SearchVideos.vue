<template>
  <div class="search-songs">
    <content-list :contentList="listOfvideos"></content-list>
  </div>
</template>

<script>
import mixin from '../../mixins'
import AlbumContent from '../AlbumContent'
import { mapGetters } from 'vuex'
import ContentList from "../ContentList";

export default {
  name: 'search-videos',
  mixins: [mixin],
  components: {
    ContentList,
  },
  computed: {
    ...mapGetters([
      'listOfSongs' // 存放的音乐
    ])
  },
  mounted () {
    this.getSong()
  }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/search-songs.scss';
</style>
