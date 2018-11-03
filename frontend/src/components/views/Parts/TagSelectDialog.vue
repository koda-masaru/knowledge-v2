<template>
  <span>
    <div class="modal fade" id="select-tag-dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span></button>
            <h4 class="modal-title">{{$t('TagSelectDialog.Title')}}</h4>
          </div>

          <div class="modal-body">
            <div class="select-area">
              <span class="select-item" v-for="tag in tags" :key="tag">
                <i class="fa fa-users text-light-blue" aria-hidden="true"></i>&nbsp;{{tag}}
                <button type="button" class="btn btn-box-tool" v-on:click="removeTag(tag)">
                  <i class="fa fa-minus-circle"></i>
                </button>
              </span>
            </div>

            <div class="row">
            <div class="col-xs-12">
              <div class="box">
                <div class="box-header">
                  <h3 class="box-title">
                    <i class="fa fa-refresh fa-spin fa-1x fa-fw" v-if="loading"></i>
                  </h3>
                  <div class="box-tools">
                    <div class="input-group input-group-sm" style="width: 200px;">
                      <input type="text" name="table_search" class="form-control pull-right" placeholder="Search" v-model="params.keyword">
                      <div class="input-group-btn">
                        <button type="submit" class="btn btn-default" v-on:click="searchTags()">
                          <i class="fa fa-search"></i>
                        </button>
                        <button type="button" class="btn btn-default input-group-sm" v-on:click="prevSearchTags()"
                          v-bind:class="{'disabled': params.offset === 0}">
                          <i class="fa fa-arrow-circle-left"></i>
                        </button>
                        <button type="button" class="btn btn-default" v-on:click="nextSearchTags()">
                          <i class="fa fa-arrow-circle-right"></i>
                       </button>
                      </div>
                    </div>
                  </div>
                </div>
               <div class="box-body table-responsive no-padding" v-if="items.length > 0">
                  <table class="table table-hover">
                    <tbody><tr>
                      <th>Tag</th>
                      <th style="width:50px;">
                        <i class="fa fa-books" aria-hidden="true"></i>
                        Count
                      </th>
                    </tr>
                    <tr v-for="item in items" :key="item.id"
                      v-bind:class="{'clickable': !item.selected, 'clickdisable': item.selected}"
                      v-on:click="selectTag(item)">
                      <td>{{item.tagName}}</td>
                      <td class="text-center">
                        {{item.knowledgeCount}}
                      </td>
                    </tr>
                 </tbody></table>
               </div>
               <div class="box-body table-responsive no-padding" v-if="items.length === 0">
                 not exists
               </div>
              </div>
            </div>
            </div>
          </div>

          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">{{$t('Label.Close')}}</button>
          </div>
        </div>
      </div>
    </div>
  </span>
</template>

<script>
import { mapState } from 'vuex'
import logger from 'logger'
const LABEL = 'TagSelectDialog.vue'

export default {
  name: 'TagSelectDialog',
  computed: {
    ...mapState({
      tags: state => state.tags.tags,
      items: state => state.tags.items,
      params: state => state.tags.params,
      loading: state => state.tags.loading
    })
  },
  data () {
    return {
    }
  },
  methods: {
    selectTag: function (tag) {
      if (!this.tags.includes(tag.tagName)) {
        this.tags.push(tag.tagName)
        this.$store.dispatch('article/addTag', tag.tagName)
      }
    },
    searchTags: function () {
      logger.debug(LABEL, 'search targets')
      this.$store.dispatch('tags/searchTags', this.params)
    },
    removeTag: function (tag) {
      this.$store.dispatch('tags/removeTag', tag)
      this.$store.dispatch('article/removeTag', tag)
    },
    nextSearchTags: function () {
      this.params.offset = this.params.offset + this.params.limit
      this.$store.dispatch('tags/searchTags', this.params)
    },
    prevSearchTags: function () {
      this.params.offset = this.params.offset - this.params.limit
      if (this.params.offset < 0) {
        this.params.offset = 0
      }
      this.$store.dispatch('tags/searchTags', this.params)
    }
  },
  mounted () {
    this.$nextTick(() => {
    })
  }
}
</script>

<style>
.select-area {
  margin-bottom: 5px;
}
.select-item {
  border: 1px solid gray;
  padding: 5px;
  border-radius: 5px;
  display: inline-block;
}
</style>
