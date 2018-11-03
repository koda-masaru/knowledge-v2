import showTagSelectDialog from './actions/showTagSelectDialog'
import searchTags from './actions/searchTags'
import removeTag from './actions/removeTag'

export default {
  namespaced: true,
  state: {
    loading: false,
    params: {
      keyword: '',
      limit: 10,
      offset: 0
    },
    tags: [],
    items: []
  },
  getters: {
  },
  actions: {
    showTagSelectDialog, searchTags, removeTag
  },
  mutations: {
  }
}
