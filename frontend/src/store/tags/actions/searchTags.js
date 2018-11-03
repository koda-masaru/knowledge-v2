import Promise from 'bluebird'
import api from '../../../api'
import logger from 'logger'

const LABEL = 'searchTags.js'

export default (store, params) => {
  logger.debug(LABEL, 'searchTags')
  store.state.loading = true
  var url = '/_api/tags'
  url += '?keyword=' + params.keyword
  url += '&offset=' + params.offset
  url += '&limit=' + params.limit

  return Promise.try(() => {
    return api.request('get', url)
  }).then(response => {
    logger.debug(LABEL, JSON.stringify(response.data))
    store.state.items = response.data
  }).catch(error => {
    logger.error(LABEL, JSON.stringify(error))
    var msg = logger.buildResponseErrorMsg(error.response, {suffix: 'Please try again.'})
    store.commit('pagestate/addAlert', {
      type: 'warning',
      display: false,
      title: 'Error',
      content: msg
    }, {root: true})
  }).finally(() => {
    store.state.loading = false
  })
}
