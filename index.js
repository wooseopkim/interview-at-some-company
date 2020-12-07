function f (A, B) {
  const aLen = A.length
  const bLen = B.length

  if (bLen === 0) {
    return true
  }

  const bIndex = B.index || 0
  const b = B[bIndex]
  
  const aIndex = A.index || 0
  for (let i = aIndex; i < aLen; ++i) {
    const a = A[i]
    
    if (a === b) {
      A.index = i + 1
      B.index = bIndex + 1
      return f(A, B)
    }
  }

  return false
}

module.exports = f
