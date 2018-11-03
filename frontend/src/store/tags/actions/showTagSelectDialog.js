/* global $ */
import Promise from 'bluebird'

import logger from 'logger'
const LABEL = 'showTagSelectDialog.js'

export default (store, params) => {
  logger.info(LABEL, 'showTagSelectDialog')
  return Promise.try(() => {
    store.state.tags = params.tags // すでに選択されているTag
    store.state.params = {
      keyword: '',
      limit: 10,
      offset: 0
    }
    store.state.items = []
    store.state.loading = false
    $('#select-tag-dialog').modal('show')
    return store.dispatch('searchTags', store.state.params)
  })
}
