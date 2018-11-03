import Promise from 'bluebird'
import logger from 'logger'
const LABEL = 'removeTag.js'

export default (store, tag) => {
  return Promise.try(() => {
    logger.debug(LABEL, 'removeTag')
    logger.info(JSON.stringify(store.state.tags))
    let array = store.state.tags
    let arrayNew = array.filter(function (v, i) {
      return (v !== tag)
    })
    store.state.tags = arrayNew
  })
}
